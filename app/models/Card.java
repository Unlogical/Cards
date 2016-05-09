package cards.models;

/**
 * Created by cyou on 12/7/15.
 */
public class Card{

    final long cardId;
    final long cardsetId;
    final String textA;
    final String textB;
    final String imageA;
    final String imageB;
    final boolean reversable;

    public Card(long cardId, long cardsetId, String textA, String textB, String imageA, String imageB, boolean reversable) {
        this.cardId = cardId;
        this.cardsetId = cardsetId;
        this.textA = textA;
        this.textB = textB;
        this.imageA = imageA;
        this.imageB = imageB;
        this.reversable = reversable;
    }

    public long getCardId() {
        return cardId;
    }

    public long getCardsetId() {
        return cardsetId;
    }

    public String getTextA() {
        return textA;
    }

    public String getTextB() {
        return textB;
    }

    public String getImageA() {
        return imageA;
    }

    public String getImageB() {
        return imageB;
    }

    public boolean isReversable() {
        return reversable;
    }
}
