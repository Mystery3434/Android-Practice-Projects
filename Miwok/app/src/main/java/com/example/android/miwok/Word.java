package com.example.android.miwok;

import android.content.Context;

/**
 * Created by Romario on 30-Aug-17.
 */

public class Word
{
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageResourceID = NO_IMAGE_PROVIDED;
    public static final int NO_IMAGE_PROVIDED = 0;
    private int mAudioResourceID;

    /**
     * Create new Word object
     * @param defaultTranslation is the language the user is using
     * @param miwokTranslation is the translation of the word in Miwok
     */

    public Word(String defaultTranslation, String miwokTranslation,  int audioResourceID)
    {
        mMiwokTranslation=miwokTranslation;
        mDefaultTranslation=defaultTranslation;
        mAudioResourceID = audioResourceID;
    }



    /**
     * Create new Word object
     * @param defaultTranslation is the language the user is using
     * @param miwokTranslation is the translation of the word in Miwok
     * @param imageResourceID is the resource ID of the image for the corresponding word
     *
     */

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceID, int audioResourceID)
    {
        mImageResourceID = imageResourceID;
        mMiwokTranslation=miwokTranslation;
        mDefaultTranslation=defaultTranslation;
        mAudioResourceID = audioResourceID;

    }

    public boolean hasImage()
    {
        if (mImageResourceID!=-NO_IMAGE_PROVIDED)
            return true;
        else
            return false;
    }

    public String getMiwokTranslation()
    {
        return mMiwokTranslation;
    }

    public String getEnglishTranslation()
    {
        return mDefaultTranslation;
    }

    public int getImageResourceID()
    {
        return mImageResourceID;
    }

    public int getAudioResourceID()
    {
        return mAudioResourceID;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mImageResourceID=" + mImageResourceID +
                ", mAudioResourceID=" + mAudioResourceID +
                '}';
    }
}
