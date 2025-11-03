import java.util.*;
import java.util.regex.*;

public class ReduceExp {

    static class Term {
        final int x, y;
        Term(int x, int y) { this.x = x; this.y = y; }
        @Override public boolean equals(Object o){
            if (!(o instanceof Term)) return false;
            Term t = (Term)o;
            return this.x==t.x && this.y==t.y;
        }
        @Override public int hashCode(){ return Objects.hash(x,y); }
    }

    static Map<Term,Integer> add(Map<Term,Integer> a, Map<Term,Integer> b){
        Map<Term,Integer> res = new HashMap<>(a);
        for (Map.Entry<Term,Integer> e : b.entrySet()){
            Term t = e.getKey();
            int val = res.getOrDefault(t,0) + e.getValue();
            if (val==0) res.remove(t); else res.put(t,val);
        }
        return res;
    }

    static Map<Term,Integer> mul(Map<Term,Integer> a, Map<Term,Integer> b){
        Map<Term,Integer> res = new HashMap<>();
        for (Map.Entry<Term,Integer> e1 : a.entrySet()){
            for (Map.Entry<Term,Integer> e2 : b.entrySet()){
                int nx = e1.getKey().x + e2.getKey().x;
                int ny = e1.getKey().y + e2.getKey().y;
                if (nx + ny > 2) continue;
                Term t = new Term(nx, ny);
                int val = res.getOrDefault(t,0) + e1.getValue() * e2.getValue();
                if (val==0) res.remove(t); else res.put(t,val);
            }
        }
        return res;
    }

    static class Parser {
        final List<String> tokens;
        int pos = 0;
        Parser(String s){ tokens = tokenize(s); }

        static List<String> tokenize(String s){
            List<String> t = new ArrayList<>();
            Pattern p = Pattern.compile("\\d+|[xy+*()]");
            Matcher m = p.matcher(s.replaceAll("\\s+",""));
            while (m.find()) t.add(m.group());
            return t;
        }
        String peek(){ return pos < tokens.size() ? tokens.get(pos) : null; }
        String next(){ return pos < tokens.size() ? tokens.get(pos++) : null; }

        Map<Term,Integer> parseFactor(){
            String tk = peek();
            if (tk == null) return constPoly(0);
            if (tk.matches("\\d+")){
                next();
                return constPoly(Integer.parseInt(tk));
            }
            if (tk.equals("x")){
                next();
                Map<Term,Integer> m = new HashMap<>();
                m.put(new Term(1,0), 1);
                return m;
            }
            if (tk.equals("y")){
                next();
                Map<Term,Integer> m = new HashMap<>();
                m.put(new Term(0,1), 1);
                return m;
            }
            if (tk.equals("(")){
                next(); // '('
                Map<Term,Integer> node = parseExpr();
                if (" )".trim().equals(peek()) || ")".equals(peek())) {
                    next();
                } else if (peek() != null) {
                    // consume if closing present
                    if (peek().equals(")")) next();
                }
                return node;
            }
            return constPoly(0);
        }

        Map<Term,Integer> parseTerm(){
            Map<Term,Integer> node = parseFactor();
            while ("*".equals(peek())){
                next();
                Map<Term,Integer> rhs = parseFactor();
                node = mul(node, rhs);
            }
            return node;
        }

        Map<Term,Integer> parseExpr(){
            Map<Term,Integer> node = parseTerm();
            while ("+".equals(peek())){
                next();
                Map<Term,Integer> rhs = parseTerm();
                node = add(node, rhs);
            }
            return node;
        }

        Map<Term,Integer> parse(){ return parseExpr(); }
    }

    static Map<Term,Integer> constPoly(int v){
        Map<Term,Integer> m = new HashMap<>();
        m.put(new Term(0,0), v);
        return m;
    }
    static int baselineCost(Map<Term,Integer> poly){
        int mults = 0, termCount = 0;
        int constant = poly.getOrDefault(new Term(0,0), 0);
        for (Map.Entry<Term,Integer> e : poly.entrySet()){
            Term t = e.getKey();
            int coeff = e.getValue();
            if (t.x==0 && t.y==0) continue;
            int deg = t.x + t.y;
            if (deg == 2){
                mults += 1; // var*var
                if (coeff != 1) mults += 1; // multiply by coefficient
            } else if (deg == 1){
                if (coeff != 1) mults += 1; // multiply by coefficient
            }
            termCount++;
        }
        int items = termCount + (constant != 0 ? 1 : 0);
        int adds = Math.max(0, items - 1);
        return mults + adds;
    }

    static List<int[]> divisorPairs(int n){
        final int B = 100;
        List<int[]> out = new ArrayList<>();
        if (n == 0){
            for (int a = -B; a <= B; a++){
                if (Math.abs(a) <= B) out.add(new int[]{0, a});
                if (Math.abs(a) <= B) out.add(new int[]{a, 0});
            }
            return uniquePairs(out);
        }
        for (int d = 1; d <= Math.abs(n); d++){
            if (n % d == 0){
                int q = n / d;
                int[][] cands = {{d,q},{-d,-q},{q,d},{-q,-d}};
                for (int[] c : cands){
                    if (Math.abs(c[0]) <= B && Math.abs(c[1]) <= B) out.add(c);
                }
            }
        }
        return uniquePairs(out);
    }
    static List<int[]> uniquePairs(List<int[]> in){
        List<int[]> out = new ArrayList<>();
        outer:
        for (int[] p : in){
            for (int[] q : out) if (p[0]==q[0] && p[1]==q[1]) continue outer;
            out.add(p);
        }
        return out;
    }
    static class Candidate { int a1,a2,b1,b2,form; Map<Term,Integer> rem; Candidate(int a1,int a2,int b1,int b2,int form, Map<Term,Integer> rem){ this.a1=a1;this.a2=a2;this.b1=b1;this.b2=b2;this.form=form;this.rem=rem;} }

    static List<Candidate> tryForm1(Map<Term,Integer> poly){
        int A = poly.getOrDefault(new Term(2,0), 0);
        int B = poly.getOrDefault(new Term(1,0), 0);
        int C = poly.getOrDefault(new Term(0,0), 0);
        List<Candidate> res = new ArrayList<>();
        List<int[]> aps = divisorPairs(A);
        List<int[]> bps = divisorPairs(C);
        for (int[] a : aps) for (int[] b : bps){
            int a1=a[0], a2=a[1], b1=b[0], b2=b[1];
            if (a1 * b2 + a2 * b1 == B){
                Map<Term,Integer> exp = new HashMap<>();
                exp.put(new Term(2,0), a1*a2);
                exp.put(new Term(1,0), a1*b2 + a2*b1);
                exp.put(new Term(0,0), b1*b2);
                Map<Term,Integer> rem = subtract(poly, exp);
                res.add(new Candidate(a1,a2,b1,b2,1, rem));
            }
        }
        return res;
    }

    static List<Candidate> tryForm2(Map<Term,Integer> poly){
        int A = poly.getOrDefault(new Term(1,1), 0);
        int B = poly.getOrDefault(new Term(1,0), 0);
        int C = poly.getOrDefault(new Term(0,1), 0);
        int D = poly.getOrDefault(new Term(0,0), 0);
        List<Candidate> res = new ArrayList<>();
        List<int[]> aps = divisorPairs(A);
        List<int[]> bps = divisorPairs(D);
        for (int[] a : aps) for (int[] b : bps){
            int a1=a[0], a2=a[1], b1=b[0], b2=b[1];
            if (a1 * b2 == B && a2 * b1 == C){
                Map<Term,Integer> exp = new HashMap<>();
                exp.put(new Term(1,1), a1*a2);
                exp.put(new Term(1,0), a1*b2);
                exp.put(new Term(0,1), a2*b1);
                exp.put(new Term(0,0), b1*b2);
                Map<Term,Integer> rem = subtract(poly, exp);
                res.add(new Candidate(a1,a2,b1,b2,2, rem));
            }
        }
        return res;
    }

    static List<Candidate> tryForm3(Map<Term,Integer> poly){
        int A = poly.getOrDefault(new Term(2,0), 0);
        int B = poly.getOrDefault(new Term(1,0), 0);
        int C = poly.getOrDefault(new Term(1,1), 0);
        int D = poly.getOrDefault(new Term(0,1), 0);
        List<Candidate> res = new ArrayList<>();
        List<int[]> aps = divisorPairs(A);
        List<int[]> bps = divisorPairs(D);
        for (int[] a : aps) for (int[] b : bps){
            int a1=a[0], a2=a[1], b1=b[0], b2=b[1];
            if (a1 * b2 == B && a2 * b1 == C){
                Map<Term,Integer> exp = new HashMap<>();
                exp.put(new Term(2,0), a1*a2);
                exp.put(new Term(1,0), a1*b2);
                exp.put(new Term(1,1), a2*b1);
                exp.put(new Term(0,1), b1*b2);
                Map<Term,Integer> rem = subtract(poly, exp);
                res.add(new Candidate(a1,a2,b1,b2,3, rem));
            }
        }
        return res;
    }

    static Map<Term,Integer> subtract(Map<Term,Integer> poly, Map<Term,Integer> exp){
        Map<Term,Integer> res = new HashMap<>(poly);
        for (Map.Entry<Term,Integer> e : exp.entrySet()){
            Term t = e.getKey();
            int nv = res.getOrDefault(t,0) - e.getValue();
            if (nv == 0) res.remove(t); else res.put(t, nv);
        }
        return res;
    }
    static Map<Term,Integer> buildProductPoly(int a1,int a2,int b1,int b2,int form){
        if (form == 1){
            // (a1 x + b1) (a2 x + b2)
            Map<Term,Integer> f1 = new HashMap<>(); f1.put(new Term(1,0), a1); f1.put(new Term(0,0), b1);
            Map<Term,Integer> f2 = new HashMap<>(); f2.put(new Term(1,0), a2); f2.put(new Term(0,0), b2);
            return mul(f1,f2);
        } else if (form == 2){
            // (a1 x + b1) (a2 y + b2)
            Map<Term,Integer> f1 = new HashMap<>(); f1.put(new Term(1,0), a1); f1.put(new Term(0,0), b1);
            Map<Term,Integer> f2 = new HashMap<>(); f2.put(new Term(0,1), a2); f2.put(new Term(0,0), b2);
            return mul(f1,f2);
        } else {
            // form 3: (a1 x + b1 y) (a2 x + b2)
            Map<Term,Integer> f1 = new HashMap<>(); f1.put(new Term(1,0), a1); f1.put(new Term(0,1), b1);
            Map<Term,Integer> f2 = new HashMap<>(); f2.put(new Term(1,0), a2); f2.put(new Term(0,0), b2);
            return mul(f1,f2);
        }
    }
    static int[] factorInternalCost(int aX, int aY, int c){
        int mults = 0, adds = 0;
        int terms = 0;
        if (aX != 0) {
            terms++;
            if (aX != 1) mults++;
        }
        if (aY != 0) {
            terms++;
            if (aY != 1) mults++;
        }
        if (c != 0) terms++;
        if (terms >= 2) adds = 1;
        int isConst = ( (aX==0 && aY==0) ? 1 : 0 );
        return new int[]{mults, adds, isConst, c};
    }
    static int costForCandidate(Candidate cand){
        Map<Term,Integer> rem = cand.rem;
        Map<Term,Integer> prodPoly = buildProductPoly(cand.a1, cand.a2, cand.b1, cand.b2, cand.form);

        Map<Term,Integer> expanded = add(prodPoly, rem);
        int costExpand = baselineCost(expanded);

        int f1_aX=0,f1_aY=0,f1_c=0;
        int f2_aX=0,f2_aY=0,f2_c=0;
        if (cand.form == 1){
            f1_aX = cand.a1; f1_c = cand.b1;
            f2_aX = cand.a2; f2_c = cand.b2;
        } else if (cand.form == 2){
            f1_aX = cand.a1; f1_c = cand.b1;
            f2_aY = cand.a2; f2_c = cand.b2;
        } else {
            f1_aX = cand.a1; f1_aY = cand.b1;
            f2_aX = cand.a2; f2_c = cand.b2;
        }
        int[] c1 = factorInternalCost(f1_aX, f1_aY, f1_c);
        int[] c2 = factorInternalCost(f2_aX, f2_aY, f2_c);
        int mults = c1[0] + c2[0];
        int adds = c1[1] + c2[1];

        boolean f1Const = c1[2]==1;
        boolean f2Const = c2[2]==1;
        int f1ConstVal = c1[3];
        int f2ConstVal = c2[3];

        boolean prodIsConstant = f1Const && f2Const;
        boolean prodIsNonconstant = !prodIsConstant;

        int extraMultForProduct = 0;
        if (prodIsConstant){
        } else {
            if (f1Const && f1ConstVal == 1) {
                extraMultForProduct = 0;
            } else if (f2Const && f2ConstVal == 1) {
                extraMultForProduct = 0;
            } else {
                extraMultForProduct = 1; // multiply two computed factor results
            }
        }
        mults += extraMultForProduct;
        int remMults = 0, remTerms = 0;
        int remConst = rem.getOrDefault(new Term(0,0), 0);
        for (Map.Entry<Term,Integer> e : rem.entrySet()){
            Term t = e.getKey();
            if (t.x==0 && t.y==0) continue;
            int coeff = e.getValue();
            int deg = t.x + t.y;
            if (deg==2){
                remMults += 1;
                if (coeff != 1) remMults += 1;
            } else {
                if (coeff != 1) remMults += 1;
            }
            remTerms++;
        }
        int finalConst = remConst;
        if (prodIsConstant) {
            finalConst += f1ConstVal * f2ConstVal;
        }

        int topItems = remTerms + (prodIsNonconstant ? 1 : 0) + (finalConst != 0 ? 1 : 0);
        int topAdds = Math.max(0, topItems - 1);

        int costEvalFactors = mults + remMults + adds + topAdds;
        return Math.min(costExpand, costEvalFactors);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()){
            System.out.println(0);
            return;
        }
        String line = sc.nextLine().trim();
        sc.close();

        Parser p = new Parser(line);
        Map<Term,Integer> poly = p.parse();

        if (poly.size() == 1 && poly.containsKey(new Term(0,0))) {
            System.out.println(0);
            return;
        }

        int best = baselineCost(poly);
        for (Candidate c : tryForm1(poly)) best = Math.min(best, costForCandidate(c));
        for (Candidate c : tryForm2(poly)) best = Math.min(best, costForCandidate(c));
        for (Candidate c : tryForm3(poly)) best = Math.min(best, costForCandidate(c));

        System.out.println(best);
    }
}

