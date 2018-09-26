package co.uglytruth.hashtag.instagram;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

public class InstagramShare {
/*
String type = "image/*";

String type = "video/*";
 */
    private void createInstagramIntent(Context context, String type, String mediaPath){

        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType(type);

        // Create the URI from the media
        File media = new File(mediaPath);
        Uri uri = Uri.fromFile(media);

        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);

        // Broadcast the Intent.
        context.startActivity(Intent.createChooser(share, "Share to"));
    }

    /*

Sharing a Background Asset


    // Define image asset URI and attribution link URL
Uri backgroundAssetUri = "your-image-asset-uri-goes-here;"
String attributionLinkUrl = "https://www.my-aweseome-app.com/p/BhzbIOUBval/";

// Instantiate implicit intent with ADD_TO_STORY action,
// background asset, and attribution link
Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
intent.setDataAndType(backgroundAssetUri, MEDIA_TYPE_JPEG);
intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
intent.putExtra("content_url", attributionLinkUrl);

// Instantiate activity and verify it will resolve implicit intent
Activity activity = getActivity();
if (activity.getPackageManager().resolveActivity(intent, 0) != null) {
  activity.startActivityForResult(intent, 0);
}
     */


    /*

Sharing a Sticker Asset



    // Define image asset URI and attribution link URL
Uri stickerAssetUri = "your-image-asset-uri-goes-here;"
String attributionLinkUrl = "https://www.my-aweseome-app.com/p/BhzbIOUBval/";

// Instantiate implicit intent with ADD_TO_STORY action,
// sticker asset, background colors, and attribution link
Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
intent.setType(MEDIA_TYPE_JPEG);
intent.putExtra("interactive_asset_uri", stickerAssetUri);
intent.putExtra("content_url", attributionLinkUrl);
intent.putExtra("top_background_color", "#33FF33");
intent.putExtra("bottom_background_color", "#FF00FF");

// Instantiate activity and verify it will resolve implicit intent
Activity activity = getActivity();
activity.grantUriPermission(
    "com.instagram.android", stickerAssetUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
if (activity.getPackageManager().resolveActivity(intent, 0) != null) {
  activity.startActivityForResult(intent, 0);
}
     */



    /*
Sharing a Background Asset and a Sticker Asset

// Define background and sticker asset URIs and attribution link URL
Uri backgroundAssetUri = "your-background-image-asset-uri-goes-here;"
Uri stickerAssetUri = "your-sticker-image-asset-uri-goes-here;"
String attributionLinkUrl = "https://www.my-aweseome-app.com/p/BhzbIOUBval/";

// Instantiate implicit intent with ADD_TO_STORY action,
// background asset, sticker asset, and attribution link
Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
intent.setDataAndType(backgroundAssetUri, MEDIA_TYPE_JPEG);
intent.putExtra("interactive_asset_uri", stickerAssetUri);
intent.putExtra("content_url", attributionLinkUrl);

// Instantiate activity and verify it will resolve implicit intent
Activity activity = getActivity();
activity.grantUriPermission(
    "com.instagram.android", stickerAssetUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
if (activity.getPackageManager().resolveActivity(intent, 0) != null) {
  activity.startActivityForResult(intent, 0);
}

     */
}
