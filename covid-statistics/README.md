 Thus utility reads data from classpath and stores data in memory, against which we can filter the data.
 
 Run the com.gan.application.Runner class which will list the summarized data and will you whether you want run **search** or exit the application.
 
 You can filter the data with
- date
- state
- country
- continent

In all the cases we get recovered/confirmed count and recovery ratio, so I couldn't find a valid reason for adding the filter by case status (recovered, confirmed or both)

The query format should be <fieldname>='<filtervalue>', <fieldname1>='<filtervalue'>

Possible examples;
- Input: date=’2020-04-16’, country=’united kingdom’; 
  Output : ‘recovered =7, confirmed=5343, recovery_ratio=0.0
- Input: date=’2020-04-19’, country=’china’, state=’shanghai’; 
  Output : ‘recovered =9, confirmed=7, recovery_ratio=0.82
- Input: date=’2021-01-01’, continent=’Europe’; 
  Output : ‘recovered =120214, confirmed=170478, recovery_ratio=0.43

  
