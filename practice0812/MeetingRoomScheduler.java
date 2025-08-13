package practice0812;

import java.util.*;

public class MeetingRoomScheduler {

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] it : intervals) {
            if (!pq.isEmpty() && pq.peek() <= it[0])
                pq.poll();
            pq.offer(it[1]);
        }
        return pq.size();
    }

    static final class Edge {
        int to, rev, cap, cost;

        Edge(int to, int rev, int cap, int cost) {
            this.to = to;
            this.rev = rev;
            this.cap = cap;
            this.cost = cost;
        }
    }

    static final class MCMF {
        final int n;
        final List<List<Edge>> g;

        MCMF(int n) {
            this.n = n;
            g = new ArrayList<>(n);
            for (int i = 0; i < n; i++)
                g.add(new ArrayList<>());
        }

        void addEdge(int u, int v, int cap, int cost) {
            Edge a = new Edge(v, g.get(v).size(), cap, cost);
            Edge b = new Edge(u, g.get(u).size(), 0, -cost);
            g.get(u).add(a);
            g.get(v).add(b);
        }

        int[] minCostMaxFlow(int s, int t, int maxf) {
            int flow = 0, cost = 0;
            int[] dist = new int[n], pv = new int[n], pe = new int[n], pot = new int[n];
            while (flow < maxf) {
                Arrays.fill(dist, Integer.MAX_VALUE / 4);
                dist[s] = 0;
                PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
                q.offer(new int[] { 0, s });
                boolean[] inq = new boolean[n];
                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int d = cur[0], u = cur[1];
                    if (d != dist[u])
                        continue;
                    for (int i = 0; i < g.get(u).size(); i++) {
                        Edge e = g.get(u).get(i);
                        if (e.cap <= 0)
                            continue;
                        int nd = d + e.cost + pot[u] - pot[e.to];
                        if (nd < dist[e.to]) {
                            dist[e.to] = nd;
                            pv[e.to] = u;
                            pe[e.to] = i;
                            if (!inq[e.to]) {
                                q.offer(new int[] { nd, e.to });
                                inq[e.to] = true;
                            }
                        }
                    }
                }
                if (dist[t] == Integer.MAX_VALUE / 4)
                    break;
                for (int i = 0; i < n; i++)
                    if (dist[i] < Integer.MAX_VALUE / 4)
                        pot[i] += dist[i];
                int add = maxf - flow;
                int v = t;
                while (v != s) {
                    Edge e = g.get(pv[v]).get(pe[v]);
                    add = Math.min(add, e.cap);
                    v = pv[v];
                }
                v = t;
                while (v != s) {
                    Edge e = g.get(pv[v]).get(pe[v]);
                    e.cap -= add;
                    g.get(v).get(e.rev).cap += add;
                    v = pv[v];
                }
                flow += add;
                cost += add * pot[t];
            }
            return new int[] { flow, cost };
        }
    }

    public static class ScheduleResult {
        public final int totalTime;
        public final List<int[]> chosen;

        ScheduleResult(int totalTime, List<int[]> chosen) {
            this.totalTime = totalTime;
            this.chosen = chosen;
        }
    }

    public static ScheduleResult maximizeTotalTime(int[][] meetings, int rooms) {
        if (meetings.length == 0 || rooms == 0)
            return new ScheduleResult(0, new ArrayList<>());
        TreeSet<Integer> ts = new TreeSet<>();
        for (int[] m : meetings) {
            ts.add(m[0]);
            ts.add(m[1]);
        }
        List<Integer> times = new ArrayList<>(ts);
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < times.size(); i++)
            idx.put(times.get(i), i);
        int m = times.size();
        int S = m, T = m + 1;
        MCMF f = new MCMF(m + 2);
        f.addEdge(S, 0, rooms, 0);
        for (int i = 0; i < m - 1; i++)
            f.addEdge(i, i + 1, rooms, 0);
        f.addEdge(m - 1, T, rooms, 0);
        List<int[]> edgeRef = new ArrayList<>();
        List<int[]> normMeet = new ArrayList<>();
        for (int[] mt : meetings) {
            if (mt[0] >= mt[1])
                continue;
            int u = idx.get(mt[0]), v = idx.get(mt[1]);
            int w = mt[1] - mt[0];
            int before = f.g.get(u).size();
            f.addEdge(u, v, 1, -w);
            edgeRef.add(new int[] { u, before });
            normMeet.add(new int[] { mt[0], mt[1] });
        }
        f.minCostMaxFlow(S, T, rooms);
        List<int[]> chosen = new ArrayList<>();
        int gain = 0;
        for (int i = 0; i < edgeRef.size(); i++) {
            int u = edgeRef.get(i)[0], ei = edgeRef.get(i)[1];
            Edge e = f.g.get(u).get(ei);
            if (e.cap == 0) {
                chosen.add(normMeet.get(i));
                gain += (normMeet.get(i)[1] - normMeet.get(i)[0]);
            }
        }
        return new ScheduleResult(gain, chosen);
    }

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } }));
        System.out.println(minMeetingRooms(new int[][] { { 9, 10 }, { 4, 9 }, { 4, 17 } }));
        System.out.println(minMeetingRooms(new int[][] { { 1, 5 }, { 8, 9 }, { 8, 9 } }));

        ScheduleResult r = maximizeTotalTime(new int[][] { { 1, 4 }, { 2, 3 }, { 4, 6 } }, 1);
        r.chosen.sort(Comparator.comparingInt(a -> a[0]));
        System.out.print("[");
        for (int i = 0; i < r.chosen.size(); i++) {
            int[] it = r.chosen.get(i);
            System.out.print("[" + it[0] + "," + it[1] + "]");
            if (i + 1 < r.chosen.size())
                System.out.print(", ");
        }
        System.out.println("], time=" + r.totalTime);
    }
}
