package adverProgram.storages;

import adverProgram.models.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdStorage {
    private List<AD> ads;

    public AdStorage(int length) {
        ads = new ArrayList<>(length);
    }

    public AdStorage() {
        ads = new ArrayList<>();
    }

    public void add(AD value) {
        ads.add(value);
    }

    public AD getAdByIndex(int index) {
        return ads.get(index);
    }

    public int getSize() {
        return ads.size();
    }

    public boolean isEmpty() {
        return ads.isEmpty();
    }

    public void printAllAds() {
        for (AD ad : ads) {
            System.out.println(ad.getTitle());
            System.out.println("Advertisement: " + ad.getCreatedDate());
            System.out.println("by: " + ad.getAuthor().getName());
            System.out.println(ad.getText());
            System.out.println("------------------------------------------");
        }
    }

    public void printAdsByCategory(Category category) {
        for (AD ad : ads) {
            if (ad.getCategory().equals(category)) {
                System.out.println(ad);
            }
        }
    }

    public void sortAdsByTitle() {
        SortByTitle byTitleComparator = new SortByTitle();
        ads.sort(byTitleComparator);
        printAllAds();
    }

    public void sortAdsByCreatDate() {
        SortByCreatDate byDateComparator = new SortByCreatDate();
        ads.sort(byDateComparator);
        printAllAds();
    }

    public void deleteAdByTitles(String title) {
        Iterator<AD> adIterator=ads.iterator();
        while(adIterator.hasNext()){
            AD nextAd=adIterator.next();
            if(nextAd.getTitle().equals(title)){
                adIterator.remove();
            }
        }
        printAllAds();
    }

    public void deleteMyAllAd (User author) {
        Iterator<AD> adIterator=ads.iterator();
        while(adIterator.hasNext()){
            AD nextAd=adIterator.next();
            if(nextAd.getAuthor().equals(author)){
                adIterator.remove();
            }
        }
        printAllAds();
    }


    public void printMyAllAds(User user) {
        for (AD ad : ads) {
            if (ad.getAuthor().equals(user)) {
                System.out.println(ad.getTitle());
                System.out.println("Advertisement: " + ad.getCreatedDate());
                System.out.println("by: " + ad.getAuthor().getName());
                System.out.println(ad.getText());
                System.out.println("------------------------------------------");
            }
        }
    }



}
