package riya.bhardwaj;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "hungrymind-mobilehub-593518188-Books")

public class BooksDO {
    private Double _iSBN;
    private String _author;
    private String _bookName;
    private String _imageUrl;
    private String _price;
    private String _publicationYear;
    private String _rating;

    @DynamoDBHashKey(attributeName = "ISBN")
    @DynamoDBAttribute(attributeName = "ISBN")
    public Double getISBN() {
        return _iSBN;
    }

    public void setISBN(final Double _iSBN) {
        this._iSBN = _iSBN;
    }
    @DynamoDBAttribute(attributeName = "Author")
    public String getAuthor() {
        return _author;
    }

    public void setAuthor(final String _author) {
        this._author = _author;
    }
    @DynamoDBAttribute(attributeName = "BookName")
    public String getBookName() {
        return _bookName;
    }

    public void setBookName(final String _bookName) {
        this._bookName = _bookName;
    }
    @DynamoDBAttribute(attributeName = "ImageUrl")
    public String getImageUrl() {
        return _imageUrl;
    }

    public void setImageUrl(final String _imageUrl) {
        this._imageUrl = _imageUrl;
    }
    @DynamoDBAttribute(attributeName = "Price")
    public String getPrice() {
        return _price;
    }

    public void setPrice(final String _price) {
        this._price = _price;
    }
    @DynamoDBAttribute(attributeName = "PublicationYear")
    public String getPublicationYear() {
        return _publicationYear;
    }

    public void setPublicationYear(final String _publicationYear) {
        this._publicationYear = _publicationYear;
    }
    @DynamoDBAttribute(attributeName = "Rating")
    public String getRating() {
        return _rating;
    }

    public void setRating(final String _rating) {
        this._rating = _rating;
    }

}
