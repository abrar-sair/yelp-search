# yelp-search
Very simple and basic demonstration of yelp fusion API.
This sample inclues following features
- Search with autocomplete
- Search filters/customoztion
- Sorting using the yelp API
- Details of business utilizing the business endpoints of Fusion API

# Location
Yelp fusion API is dependent on user's location and it requires to pass location (address/city/state/zip code) or Latitue/Longitues.
This Sample make use of Fused location provider to get current location of the user. Requesting runtime location permission is also handled

# Third party libraries
- Retrofit (network)
- Picaso (Image loading)
- Gson (Json parsong)
- Floating search view (Search)
- RXJava (Threading and callbacks)

# Design patteren 
- MVP design patteren is used to develp this sample project.
