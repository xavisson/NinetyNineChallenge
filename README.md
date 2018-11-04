# NinetyNineChallenge
The mobile application shows a list of companies ordered by share price. Every time the user taps on a company name, the application shows a detailed company page/view. 
As each company share price changes almost in real time, this price is refreshed every 10 secs in order to show the current real share price.

The app has been developed using Kotlin in a Clean way, along with tools such as RxJava, Dagger2 and Retrofit2.


# Architecture
A diagram of the chosen architecture is shown below. It consists in an approach of Clean Architecture in which the business logic of the app is placed in the Resource. This piece creates a recurrent query to get a list of companies and publishes the results in a stream of data that is listened by the use cases.
![](/screenshots/architecture.svg)

# Considerations

###### CompanyResource and stream of data
This piece is the center of the app architecture. It queries to get the companies at a given pace, and contains a stream in which the results of the queries are published. Then, there can be useCases subscribed to this stream so that every time there's new data in the pipe they are notified.
This Resource is a Singleton because both CompanyListPresenter and CompanyDetailPresenter are listening to updates in the stream, and then Dagger2 won't create another instance of it.

###### No Repository implementation
The statement indicates that the app must be refreshed at a pace of 20 seconds -at least- in order to show the companies real share price. Therefore I understand there is no use in implementing a Repository that decides wheather to get the data from the api or from the cache, since the app shouldn't show a cached value of the share price that would be in any case out-of-date.

###### List of Companies
The list has been implemented using [DiffUtil](https://developer.android.com/reference/android/support/v7/util/DiffUtil), as an exercise to get to know this utility class. The adapter uses delegation (`Delegates.observable`) to notice changes in the list, and a extension function that could potentialy be used by another adapter to inspect the differences in the items.

###### Share Price currency
Even though there's no info on the currency of the shared price in the data receive from the backend, I have decided to add a default unit, which us USD.
After checking different softwares showing stock values and learning that some of them use this unit ($) and some other just show the numerical value, I took the license to add the currency so that the app had a bit more of sauce.



# Screenshots
List of companies           |  Detail view
:-------------------------:|:-------------------------:
![](/screenshots/list.png)  |  ![](/screenshots/detail.png)

