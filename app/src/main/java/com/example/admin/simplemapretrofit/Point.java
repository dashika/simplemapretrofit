
package com.example.admin.simplemapretrofit;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Point implements Parcelable
{

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("title")
    @Expose
    private String title;
    public final static Creator<Point> CREATOR = new Creator<Point>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Point createFromParcel(Parcel in) {
            return new Point(in);
        }

        public Point[] newArray(int size) {
            return (new Point[size]);
        }

    }
    ;

    protected Point(Parcel in) {
        this.lat = ((Double) in.readValue((Double.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.lng = ((Double) in.readValue((Double.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Point() {
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lat);
        dest.writeValue(description);
        dest.writeValue(id);
        dest.writeValue(lng);
        dest.writeValue(title);
    }

    public int describeContents() {
        return  0;
    }

}
