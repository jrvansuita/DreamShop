# VanHackathon - Team //TODO: - Dream&Shop App

This is a project from Vanhackathon 2.0 (October 2016) and was created for the challenges of Dreamify and Shopify.
We had to create a new feed concept for dreamify, and a new sales channel for Shopify. So we created a single app who people can create 
a dream, something that want to achieve, and then, people can attach Shopify products, Youtube videos or images from gallery that may ilustrate 
what will be mandatory to complete that dream.

 * Dreamify's Dream Feed: propose a feed concept that is different from pinterest, facebook, twitter or instagram.
 * Shopify's The Future of Commerce: create a project that represents The Future of Commerce.

 
 The idea

Thinking about the idea of diversify social networks feeds, and also accounting for Dreamify's dream concept, we thought of linking together into a feed entry everything related to a dream's accomplishment. Therefore, a user could post any kind of media relative to the dream, such as photos, videos, songs, etc. And all media would compose one single feed entry, and looking at this feed should make the user feel the dream.

So, for accomplishing that dream, the user might need some products and/or services as well. Products and services could also be part of a dream entry on the feed, where users could add them as a wish list, for buying in any point in the future as part of the dream accomplishment. Furthermode, this could start some sort of donations and crowdfunding, where other people on the network could contribute for buying stuff needed for the dream.

This is where Shopify comes in! With its large network of vendors, Shopify could integrate on the dreams needs, so users could search for stuff on any shops in Shopify's platform, and also Shopify could advertise products that are related to users dreams! This is what we see as the future of commerce, where target audiences are even more specific, and can be achieved through social networks, generating higher conversion.

To illustrate our idea, we developed an MVP app for Android/iOS where a user can log in, see the dreams feed and post dreams to the feed. For creating dreams, the user can post photos, youtube videos and products from Shopify. Also, form the feed, a user could directly buy products, following the shopping flow to the end of checkout.

## Videos
 * [iOS sample video](https://youtu.be/21728hNrxsI)
 * [Android sample video](https://youtu.be/8aYSKTKC4gY)

### Project depencendies. 
 * [Google Firebase Realtime Database](https://firebase.google.com/docs/database/)
 * [Google Firebase Storage](https://firebase.google.com/docs/storage/)
 * [Google Firebase Auth](https://firebase.google.com/docs/auth/)
 * [Material Dialog](https://github.com/drakeet/MaterialDialog)
 * [Google Play Services](https://play.google.com/store/apps/details?id=com.google.android.gms&hl=en)
 * [Picasso](http://square.github.io/picasso/)
 * [Rounded Image View] (https://github.com/vinc3m1/RoundedImageView)
 * [Retrofit](http://square.github.io/retrofit/)
 * [Gson](https://github.com/google/gson)
 * [Youtube Android Player API](https://developers.google.com/youtube/android/player/).
 * [Shopify API REST](https://help.shopify.com/api/reference) 
 
 
### REST API (Developed by @taylorrf).
 * [Source Code](https://github.com/taylorrf/dreamwishlist_api)
 * [Spec] (https://dreamwishlist-api.herokuapp.com)

### IOS App (Developed by @cassianomonteiro)
  * [Source Code](https://github.com/cassianomonteiro/vanhackathon2)

 
### Assets.
 * [Icons](https://design.google.com/icons/#ic_arrow_forward)
 * [Material](https://www.materialpalette.com)


### My Developed knowledges.
 * Learned how to integrate Firebase Storage.
 * Learned how to use Youtube Android Player API
 * Learned how to use Shopify API REST (Not Shopify Android SDK).
 
 >*I couldn't use [Shopify Android SDK](https://help.shopify.com/api/sdks/mobile-buy-sdk/android) because this library uses an old version of Android Google Play Services(8.4.0).
 >The Google Firebase, also used in this project, is only compatible with Google Play Services 9.0.0 and above.*
 >An [issue](https://github.com/Shopify/mobile-buy-sdk-android/issues/319) was created on [Shopify Android SQK GitHuhb repository](https://github.com/Shopify/mobile-buy-sdk-android).
 >I also created a [question](http://stackoverflow.com/questions/40191325/multiple-dex-files-define-lcom-google-android-gms-internal-zzrx) on StackOverflow about this issue.

 > *This is app was created in 2 days and will not be continued.
 > You can check the [screenshots](/screenshots/) and the [apk file](/apk/).*
