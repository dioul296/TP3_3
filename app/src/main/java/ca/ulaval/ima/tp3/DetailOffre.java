package ca.ulaval.ima.tp3;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailOffre implements Parcelable {
    private String modelename;
    private String marquename;
    private String transmission;
    private String nomVendeur;
    private String prenomVendeur;
    private String mailVendeur;
    private String descriptionOffre;
    private String dateOffre;
    private String urlimage;
    private Integer prixOffre;
    private Integer kilometrage;
    private Integer anneemodele;

    public DetailOffre(String modelename, String marquename, String transmission, String nomVendeur, String prenomVendeur, String mailVendeur, String descriptionOffre,
                       String dateOffre, String urlimage, Integer prixOffre, Integer kilometrage, Integer anneemodele) {
        this.modelename = modelename;
        this.marquename = marquename;
        this.transmission = transmission;
        this.nomVendeur = nomVendeur;
        this.prenomVendeur = prenomVendeur;
        this.mailVendeur = mailVendeur;
        this.descriptionOffre = descriptionOffre;
        this.dateOffre = dateOffre;
        this.urlimage = urlimage;
        this.prixOffre = prixOffre;
        this.kilometrage = kilometrage;
        this.anneemodele = anneemodele;
    }

    protected DetailOffre(Parcel in) {
        modelename = in.readString();
        marquename = in.readString();
        transmission = in.readString();
        nomVendeur = in.readString();
        prenomVendeur = in.readString();
        mailVendeur = in.readString();
        descriptionOffre = in.readString();
        dateOffre = in.readString();
        urlimage = in.readString();
        if (in.readByte() == 0) {
            prixOffre = null;
        } else {
            prixOffre = in.readInt();
        }
        if (in.readByte() == 0) {
            kilometrage = null;
        } else {
            kilometrage = in.readInt();
        }
        if (in.readByte() == 0) {
            anneemodele = null;
        } else {
            anneemodele = in.readInt();
        }
    }

    public static final Creator<DetailOffre> CREATOR = new Creator<DetailOffre>() {
        @Override
        public DetailOffre createFromParcel(Parcel in) {
            return new DetailOffre(in);
        }

        @Override
        public DetailOffre[] newArray(int size) {
            return new DetailOffre[size];
        }
    };

    public String getModelename() {
        return modelename;
    }

    public void setModelename(String modelename) {
        this.modelename = modelename;
    }

    public String getMarquename() {
        return marquename;
    }

    public void setMarquename(String marquename) {
        this.marquename = marquename;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getNomVendeur() {
        return nomVendeur;
    }

    public void setNomVendeur(String nomVendeur) {
        this.nomVendeur = nomVendeur;
    }

    public String getPrenomVendeur() {
        return prenomVendeur;
    }

    public void setPrenomVendeur(String prenomVendeur) {
        this.prenomVendeur = prenomVendeur;
    }

    public String getMailVendeur() {
        return mailVendeur;
    }

    public void setMailVendeur(String mailVendeur) {
        this.mailVendeur = mailVendeur;
    }

    public String getDescriptionOffre() {
        return descriptionOffre;
    }

    public void setDescriptionOffre(String descriptionOffre) {
        this.descriptionOffre = descriptionOffre;
    }

    public String getDateOffre() {
        return dateOffre;
    }

    public void setDateOffre(String dateOffre) {
        this.dateOffre = dateOffre;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    public Integer getPrixOffre() {
        return prixOffre;
    }

    public void setPrixOffre(Integer prixOffre) {
        this.prixOffre = prixOffre;
    }

    public Integer getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Integer kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Integer getAnneemodele() {
        return anneemodele;
    }

    public void setAnneemodele(Integer anneemodele) {
        this.anneemodele = anneemodele;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(modelename);
        dest.writeString(marquename);
        dest.writeString(transmission);
        dest.writeString(nomVendeur);
        dest.writeString(prenomVendeur);
        dest.writeString(mailVendeur);
        dest.writeString(descriptionOffre);
        dest.writeString(dateOffre);
        dest.writeString(urlimage);
        if (prixOffre == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(prixOffre);
        }
        if (kilometrage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(kilometrage);
        }
        if (anneemodele == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(anneemodele);
        }
    }
}
