# fashion_feelings_compare
Fashion Feelings Compare (FFC) is a web crawler in Java. This crawler crawls some fashion website to get the cover page and compare the facial expression of the models.<br>
To compare the models, I have implemented the API of Google "Google Vision API" in the program.<br>
<h2> How does it works ?</h2>
First, we call the crawlers one by one. These crawlers will get the URL of the image of the cover page using the CSS selector.<br>
If we found a link, the picture is encoded in Base64 and we return the string. This string is send to the API of Google in a POST request.<br>
The API will return a JSON which we save in a folder that we need to specify.<br>
<h2>How do I use it ?</h2>
You need to specify the path of the foler where you want to keep the JSON files as the first argument and your key of the API as the second argument.
<h2>What's next ?</h2>
The next step is to keep the JSON in an ElasticSearch database and compare the data.
<h2>Libraries used</h2>
FFC is a maven project. I use JSOUP to parse the HTML documents.<br>
I will use the ElasticSearch library when I will implement it.
