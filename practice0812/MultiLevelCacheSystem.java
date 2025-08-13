package practice0812;

import java.util.*;

public class MultiLevelCacheSystem {

    static final class Entry {
        final int key;
        String value;
        int level;
        long freq;
        long last;
        long ver;

        Entry(int key, String value, int level, long now) {
            this.key = key;
            this.value = value;
            this.level = level;
            this.freq = 1;
            this.last = now;
            this.ver = 0;
        }

        void touch(long now) {
            freq++;
            last = now;
            ver++;
        }
    }

    static final class Node {
        final int key;
        final long ver;
        final long freq;
        final long last;

        Node(int key, long ver, long freq, long last) {
            this.key = key;
            this.ver = ver;
            this.freq = freq;
            this.last = last;
        }
    }

    static final class Level {
        final int cap;
        final int cost;
        final PriorityQueue<Node> pq;
        final Set<Integer> keys = new HashSet<>();

        Level(int cap, int cost) {
            this.cap = cap;
            this.cost = cost;
            this.pq = new PriorityQueue<>((a, b) -> {
                if (a.freq != b.freq)
                    return Long.compare(a.freq, b.freq);
                if (a.last != b.last)
                    return Long.compare(a.last, b.last);
                return Integer.compare(a.key, b.key);
            });
        }
    }

    public static final class MultiCache {
        final Level[] L;
        final Map<Integer, Entry> map = new HashMap<>();
        long clock = 0;

        public MultiCache(int c1, int c2, int c3, int cost1, int cost2, int cost3) {
            L = new Level[] { new Level(c1, cost1), new Level(c2, cost2), new Level(c3, cost3) };
        }

        private void offerNode(Entry e) {
            L[e.level].pq.offer(new Node(e.key, e.ver, e.freq, e.last));
        }

        private void ensureCapacityUpward(int lvl) {
            while (L[lvl].keys.size() > L[lvl].cap) {
                Integer ev = evictFromLevel(lvl);
                if (ev == null)
                    break;
                Entry e = map.get(ev);
                if (e == null)
                    continue;
                if (lvl + 1 >= L.length) {
                    L[lvl].keys.remove(ev);
                    map.remove(ev);
                } else {
                    L[lvl].keys.remove(ev);
                    e.level = lvl + 1;
                    L[lvl + 1].keys.add(ev);
                    offerNode(e);
                    ensureCapacityUpward(lvl + 1);
                }
            }
        }

        private Integer evictFromLevel(int lvl) {
            PriorityQueue<Node> pq = L[lvl].pq;
            while (!pq.isEmpty()) {
                Node n = pq.poll();
                Entry e = map.get(n.key);
                if (e == null)
                    continue;
                if (e.level != lvl)
                    continue;
                if (e.ver != n.ver)
                    continue;
                return n.key;
            }
            return null;
        }

        private void promoteIfNeeded(Entry e) {
            while (e.level > 0) {
                int cur = e.level;
                Integer cand = evictPeekKey(cur - 1);
                boolean shouldPromote = cand == null;
                if (!shouldPromote) {
                    Entry worstUpper = map.get(cand);
                    if (worstUpper == null || worstUpper.level != cur - 1)
                        shouldPromote = true;
                    else {
                        if (e.freq > worstUpper.freq || (e.freq == worstUpper.freq && e.last > worstUpper.last)) {
                            shouldPromote = true;
                        }
                    }
                }
                if (!shouldPromote)
                    break;
                L[cur].keys.remove(e.key);
                e.level = cur - 1;
                L[cur - 1].keys.add(e.key);
                offerNode(e);
                ensureCapacityUpward(cur - 1);
            }
        }

        private Integer evictPeekKey(int lvl) {
            PriorityQueue<Node> pq = L[lvl].pq;
            while (!pq.isEmpty()) {
                Node n = pq.peek();
                Entry e = map.get(n.key);
                if (e == null || e.level != lvl || e.ver != n.ver) {
                    pq.poll();
                    continue;
                }
                return n.key;
            }
            return null;
        }

        public String get(int key) {
            Entry e = map.get(key);
            if (e == null)
                return null;
            e.touch(++clock);
            offerNode(e);
            promoteIfNeeded(e);
            return e.value;
        }

        public void put(int key, String value) {
            Entry e = map.get(key);
            if (e != null) {
                e.value = value;
                e.touch(++clock);
                offerNode(e);
                promoteIfNeeded(e);
                return;
            }
            e = new Entry(key, value, 0, ++clock);
            map.put(key, e);
            L[0].keys.add(key);
            offerNode(e);
            ensureCapacityUpward(0);
        }

        public Map<String, List<Integer>> snapshot() {
            Map<String, List<Integer>> res = new LinkedHashMap<>();
            for (int i = 0; i < L.length; i++) {
                List<Integer> list = new ArrayList<>(L[i].keys);
                Collections.sort(list);
                res.put("L" + (i + 1), list);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        MultiCache cache = new MultiCache(2, 5, 10, 1, 3, 10);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache.snapshot());

        cache.get(1);
        cache.get(1);
        cache.get(2);
        System.out.println(cache.snapshot());

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        System.out.println(cache.snapshot());
    }
}
