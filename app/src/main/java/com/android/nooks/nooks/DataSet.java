package com.android.nooks.nooks;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by SAYAN on 04-03-2016.
 */
public class DataSet {

    public static ArrayList<Information> starred = new ArrayList<>();
    static boolean petsDataGenerated = false;
    public static ArrayList<Information> dogCatData = new ArrayList<>();
    public static ArrayList<Information> deerParrotData = new ArrayList<>();

    public static void setPetsData() {
        int images[];
        String caption[];
        String details[];
        int ids[];

        //FOR CATS AND DOGS
        images = new int[]{R.drawable.ani_cat_five,
                R.drawable.ani_cat_four,
                R.drawable.ani_cat_one,
                R.drawable.ani_cat_seven,
                R.drawable.ani_cat_six,
                R.drawable.ani_cat_three,
                R.drawable.ani_cat_two,
                R.drawable.ani_dog_four,
                R.drawable.ani_dog_one,
                R.drawable.ani_dog_three,
                R.drawable.ani_dog_two,
                R.drawable.ani_dog_five};
        caption = new String[]{"Cat?",
                "Cat",
                "Cat",
                "Cat",
                "Cat",
                "Cat",
                "Cat",
                "Dog",
                "Dog",
                "Dog",
                "Dog",
                "Dog"};
        details = new String[]{"Cat details",
                "Cat Details",
                "Cat Details",
                "Cat Details",
                "Cat Details",
                "Cat Details",
                "Cat Details",
                "Cat Details",
                "Dogs Details",
                "Dogs Details",
                "Dogs Details",
                "Dogs Details"};
        ids = new int[]{201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212};
        for (int i = 0; i < images.length && i < caption.length; i++) {
            Information information = new Information();
            information.imageId = images[i];
            information.title = caption[i];
            information.details = details[i];
            information.starred = false;
            information.dataId = ids[i];
            information.dataName="CatDog";
            dogCatData.add(information);
        }


        //FOR DEERS AND PARROTS

        images = new int[]{R.drawable.ani_deer_four,
                R.drawable.ani_deer_one,
                R.drawable.ani_deer_two,
                R.drawable.ani_deer_three,
                R.drawable.bird_parrot_one,
                R.drawable.bird_parrot_two,
                R.drawable.bird_parrot_three,
                R.drawable.bird_parrot_four,
                R.drawable.bird_parrot_five,
                R.drawable.bird_parrot_seven,
                R.drawable.bird_parrot_six,
                R.drawable.bird_parrot_eight};
        caption = new String[]{"Deer",
                "Deer",
                "Deer",
                "Deer",
                "Parrot",
                "Parrot",
                "Parrot",
                "Parrot",
                "Parrot",
                "Parrot",
                "Parrot",
                "Parrot"};
        details = new String[]{"Deers Details",
                "Deers Details",
                "Deers Details",
                "Deers Details",
                "Parrot Details",
                "Parrot Details",
                "Parrot Details",
                "Parrot Details",
                "Parrot Details",
                "Parrot Details",
                "Parrot Details",
                "Parrot Details"};
        ids = new int[]{101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112};

        for (int i = 0; i < images.length && i < caption.length; i++) {
            Information information = new Information();
            information.imageId = images[i];
            information.title = caption[i];
            information.details = details[i];
            information.starred = false;
            information.dataId = ids[i];
            information.dataName="deerParrot";
            deerParrotData.add(information);
        }
    }

    public static void addStarred(Information data) {
        starred.add(data);
        Log.d("App", "Added data of" + data.title);
    }

    public static void removeStarred(Information data) {
        starred.remove(data);
        Log.d("App", "Removed data of" + data.title);
    }

    public static ArrayList<Information> getStarredData() {
        return starred;
    }

    public static ArrayList<Information> getPetsData(String string){
        if(string.equalsIgnoreCase("CatDog"))
            return dogCatData;
        else
            return deerParrotData;
    }





    static boolean healthDataGenerated = false;
    public static ArrayList<Information> health = new ArrayList<>();
    public static void setHealthData(){
        healthDataGenerated=true;
        int image[]=new int[]{R.drawable.ani_dog_one,R.drawable.ani_dog_two,R.drawable.ani_dog_three,R.drawable.ani_dog_four,R.drawable.ani_dog_five};
        for(int i=0;i<5;i++)
        {
            Information data=new Information();
            data.details="Product name";
            data.details2="Product det";
            data.details3="Product det";
            data.imageId=image[i];
            health.add(data);
        }
    }
}
