package SimilarityMetric;

import java.util.ArrayList;

public class CosineSimilarity {


    ArrayList<Double> VectA;
    ArrayList<Double> VectB;

    public CosineSimilarity(ArrayList<Double> VectA, ArrayList<Double> VectB) {
        this.VectA = VectA;
        this.VectB = VectB;
    }

    public double similarity() {

        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;
        int iterationLimit;

        // Checks Size of Vectors and Make the vector of the smallest size the iterator of the dot prod/magnitude loops
        if (VectA.size() > VectB.size()) {

            final double index = VectA.size() - VectB.size();
            for (int j = 0; j < index; j++) {
                VectB.add(0.0);
            }


            for (int i = 0; i < VectA.size(); i++) //docVector1 and docVector2 must be of same length
            {

                dotProduct += VectA.get(i) * VectB.get(i);  // (a.b) + (a1*b1)+(a2*b2)+ ...
                magnitude1 += Math.pow(VectA.get(i), 2);  //   (a)^2 + (a1)^2 + (a2)^2 + (a3)^2 + ...
                magnitude2 += Math.pow(VectB.get(i), 2); //    (b)^2 + (b1)^2 + (b2)^2 + (b3)^2 + ...

            }
        } else if (VectA.size() < VectB.size()) {

            final double index = VectB.size() - VectA.size();
            for (int j = 0; j < index; j++) {
                VectA.add(0.0);
            }

            for (int i = 0; i < VectB.size(); i++) //docVector1 and docVector2 must be of same length
            {
                dotProduct += VectA.get(i) * VectB.get(i);  // (a.b) = (a1*b1)+(a2*b2)+ ...
                magnitude1 += Math.pow(VectA.get(i), 2);  //   (a)^2 + (a1)^2 + (a2)^2 + (a3)^2 + ...
                magnitude2 += Math.pow(VectB.get(i), 2); //    (b)^2 + (b1)^2 + (b2)^2 + (b3)^2 + ...
            }

        } else {
            for (int i = 0; i < VectA.size(); i++) //docVector1 and docVector2 must be of same length
            {
                dotProduct += VectA.get(i) * VectB.get(i);  // (a.b) + (a1*b1)+(a2*b2)+ ...
                magnitude1 += Math.pow(VectA.get(i), 2);  //   (a)^2 + (a1)^2 + (a2)^2 + (a3)^2 + ...
                magnitude2 += Math.pow(VectB.get(i), 2); //    (b)^2 + (b1)^2 + (b2)^2 + (b3)^2 + ...
            }
        }

        magnitude1 = Math.sqrt(magnitude1);//sqrt(a^2)
        magnitude2 = Math.sqrt(magnitude2);//sqrt(b^2)

        if (magnitude1 != 0.0 | magnitude2 != 0.0) {
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
        } else {
            return 0.0;
        }
        return cosineSimilarity;
    }


}