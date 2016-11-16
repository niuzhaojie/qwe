package com.niuzhaojie.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by niuzhaojie on 16/11/16.
 */

public class Person implements Parcelable {

    private String name;
    private int age;

    public Person(Parcel source) {
        name = source.readString();//代码顺序不能乱，下面怎么写，上面怎么读
        age = source.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeInt(age);

    }

    public static final Parcelable.Creator<Person> CREATOR = new Creator<Person>() {

        @Override
        public Person createFromParcel(Parcel source) {


            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
