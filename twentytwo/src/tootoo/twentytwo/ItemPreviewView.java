package tootoo.twentytwo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class ItemPreviewView extends View{
    private String mExampleString; // TODO: use a default from R.string...
    
    private Drawable mExampleDrawable;
    
    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;
    
    public ItemPreviewView(Context context)
    {
        super(context);
        init(null, 0);
    }
    
    public ItemPreviewView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs, 0);
    }
    
    public ItemPreviewView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }
    
    private void init(AttributeSet attrs, int defStyle){
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ItemPreviewView, defStyle, 0);
        
        mExampleString = a.getString(R.styleable.ItemPreviewView_exampleString);
        
        if(a.hasValue(R.styleable.ItemPreviewView_exampleDrawable))
        {
            mExampleDrawable = a.getDrawable(R.styleable.ItemPreviewView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }
        
        a.recycle();
        
        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        
        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }
    
    private void invalidateTextPaintAndMeasurements(){
        mTextWidth = mTextPaint.measureText(mExampleString);
        
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }
    
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        
        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        
        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;
        
        // Draw the text.
        canvas.drawText(mExampleString, paddingLeft + (contentWidth - mTextWidth) / 2, paddingTop + (contentHeight + mTextHeight) / 2, mTextPaint);
        
        // Draw the example drawable on top of the text.
        if(mExampleDrawable != null)
        {
            mExampleDrawable.setBounds(paddingLeft, paddingTop, paddingLeft + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
        }
    }
    
    /**
     * Gets the example string attribute value.
     * 
     * @return The example string attribute value.
     */
    public String getExampleString(){
        return mExampleString;
    }
    
    /**
     * Sets the view's example string attribute value. In the example view, this
     * string is the text to draw.
     * 
     * @param exampleString
     *            The example string attribute value to use.
     */
    public void setExampleString(String exampleString){
        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }
    
    /**
     * Gets the example drawable attribute value.
     * 
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable(){
        return mExampleDrawable;
    }
    
    /**
     * Sets the view's example drawable attribute value. In the example view,
     * this drawable is drawn above the text.
     * 
     * @param exampleDrawable
     *            The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable){
        mExampleDrawable = exampleDrawable;
    }
}