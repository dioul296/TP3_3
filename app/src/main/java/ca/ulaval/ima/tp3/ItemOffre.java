package ca.ulaval.ima.tp3;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemOffre implements Parcelable {
    private String imageItemOffre;
    private Integer prixItemOffre;
    private String marqueItemOffre;
    private String modeleItemOffre;
    private Integer kilomItemOffre;
    private  String dateItemOffre;

    public ItemOffre(String imageItemOffre, Integer prixItemOffre, String marqueItemOffre, String modeleItemOffre, Integer kilomItemOffre, String dateItemOffre) {
        this.imageItemOffre = imageItemOffre;
        this.prixItemOffre = prixItemOffre;
        this.marqueItemOffre = marqueItemOffre;
        this.modeleItemOffre = modeleItemOffre;
        this.kilomItemOffre = kilomItemOffre;
        this.dateItemOffre = dateItemOffre;
    }

    protected ItemOffre(Parcel in) {
        imageItemOffre = in.readString();
        if (in.readByte() == 0) {
            prixItemOffre = null;
        } else {
            prixItemOffre = in.readInt();
        }
        marqueItemOffre = in.readString();
        modeleItemOffre = in.readString();
        if (in.readByte() == 0) {
            kilomItemOffre = null;
        } else {
            kilomItemOffre = in.readInt();
        }
        dateItemOffre = in.readString();
    }

    public static final Creator<ItemOffre> CREATOR = new Creator<ItemOffre>() {
        @Override
        public ItemOffre createFromParcel(Parcel in) {
            return new ItemOffre(in);
        }

        @Override
        public ItemOffre[] newArray(int size) {
            return new ItemOffre[size];
        }
    };

    public String getImageItemOffre() {
        return imageItemOffre;
    }

    public void setImageItemOffre(String imageItemOffre) {
        this.imageItemOffre = imageItemOffre;
    }

    public Integer getPrixItemOffre() {
        return prixItemOffre;
    }

    public void setPrixItemOffre(Integer prixItemOffre) {
        this.prixItemOffre = prixItemOffre;
    }

    public String getMarqueItemOffre() {
        return marqueItemOffre;
    }

    public void setMarqueItemOffre(String marqueItemOffre) {
        this.marqueItemOffre = marqueItemOffre;
    }

    public String getModeleItemOffre() {
        return modeleItemOffre;
    }

    public void setModeleItemOffre(String modeleItemOffre) {
        this.modeleItemOffre = modeleItemOffre;
    }

    public Integer getKilomItemOffre() {
        return kilomItemOffre;
    }

    public void setKilomItemOffre(Integer kilomItemOffre) {
        this.kilomItemOffre = kilomItemOffre;
    }

    public String getDateItemOffre() {
        return dateItemOffre;
    }

    public void setDateItemOffre(String dateItemOffre) {
        this.dateItemOffre = dateItemOffre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageItemOffre);
        if (prixItemOffre == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(prixItemOffre);
        }
        dest.writeString(marqueItemOffre);
        dest.writeString(modeleItemOffre);
        if (kilomItemOffre == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(kilomItemOffre);
        }
        dest.writeString(dateItemOffre);
    }
}
