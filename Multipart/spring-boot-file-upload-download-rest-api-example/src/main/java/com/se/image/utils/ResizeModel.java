package com.se.image.utils;

public class ResizeModel {
    private int dx;
    private int dy;
    private int previewWidth;
    private int previewHeight;
    private  int coppedWidth;
    private int croppedHeight;

    public ResizeModel() {

    }

    public ResizeModel(int dx, int dy, int previewWidth, int previewHeight, int coppedWidth, int croppedHeight) {
        this.dx = dx;
        this.dy = dy;
        this.previewWidth = previewWidth;
        this.previewHeight = previewHeight;
        this.coppedWidth = coppedWidth;
        this.croppedHeight = croppedHeight;
    }


    //    public ResizeModel(int dx, int dy, int preview_width, int preview_height, int width, int recomendedHeight) {
//    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(int previewWidth) {
        this.previewWidth = previewWidth;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(int previewHeight) {
        this.previewHeight = previewHeight;
    }

    public int getCoppedWidth() {
        return coppedWidth;
    }

    public void setCoppedWidth(int coppedWidth) {
        this.coppedWidth = coppedWidth;
    }

    public int getCroppedHeight() {
        return croppedHeight;
    }

    public void setCroppedHeight(int croppedHeight) {
        this.croppedHeight = croppedHeight;
    }
}
