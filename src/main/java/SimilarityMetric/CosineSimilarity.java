package SimilarityMetric;

import java.util.ArrayList;

public class CosineSimilarity {


    ArrayList<Double> VectA;
    ArrayList<Double> VectB;

    public CosineSimilarity(ArrayList<Double> VectA, ArrayList<Double> VectB) {
        this.VectA = VectA;
        this.VectB = VectB;
    }

    public double similarity(){

        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;

        for (int i = 0; i < VectA.size(); i++) //docVector1 and docVector2 must be of same length
        {
            if(VectB.size() < VectA.size()) {
            VectA.remove(VectA.size()-1-i);
            }
            else if(VectB.size() > VectA.size()) {
                VectB.remove(VectB.size() -1-i);
            }

            dotProduct += VectA.get(i) * VectB.get(i);  //a.b
            magnitude1 += Math.pow(VectA.get(i), 2);  //(a^2)
            magnitude2 += Math.pow(VectB.get(i), 2); //(b^2)

        }


        magnitude1 = Math.sqrt(magnitude1);//sqrt(a^2)
        magnitude2 = Math.sqrt(magnitude2);//sqrt(b^2)

        if (magnitude1 != 0.0 | magnitude2 != 0.0)
        {
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
        }
        else
        {
            return 0.0;
        }
        System.out.println("Similarity Completed");
        return cosineSimilarity;
    }



}
