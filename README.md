1. Which API did you choose and why?
- I chose the JSONPlaceholder API (/posts). I selected it because it is a free, stable, and simple REST API perfect for testing network calls and displaying data lists.

2. How did you implement data fetching and JSON parsing?
- Fetching: I used Retrofit to handle the network requests.

- Parsing: I used Gson Converter to automatically turn the JSON data into a Kotlin Data Class.

- Threading: I used Kotlin Coroutines (lifecycleScope) so the app doesn't freeze while waiting for data.

3. What challenges did you face when handling errors or slow connections?
- The main challenge was managing the app when there was no internet. I had to use a try-catch block to prevent the app from crashing and added a ProgressBar and Toast notifications to tell the user that the data was loading or if the connection failed.

4. How would you improve your app's UI or performance in future versions?
- I would use a RecyclerView instead of a ScrollView to make the list smoother and more memory-efficient. For the UI, I would add a "Retry" button and more modern animations like Shimmer effects while loading.
