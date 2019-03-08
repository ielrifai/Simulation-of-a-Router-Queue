import java.lang.*;

public class QueueSimulator{
  public enum Event { ARRIVAL, DEPARTURE };
  private double currTime;
  private double arrivalRate;
  private double serviceTime;
  private double timeForNextArrival;
  private double timeForNextDeparture;
  private double totalSimTime;
  LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
  LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
  private Event e;
  
  public double getRandTime(double arrivalRate){
    double num, time1, max=1, min=0, randNUM;
    randNUM= Math.random();
    time1= (-1/arrivalRate) * (Math.log(1-randNUM));
    //System.out.println(time1);
    return time1;
  }

  public QueueSimulator(double aR, double servT, double simT){
    arrivalRate = aR;
    serviceTime= servT;
    timeForNextArrival = getRandTime(arrivalRate);
    timeForNextDeparture = serviceTime + timeForNextArrival;
    totalSimTime = simT;
  }
  
  public double calcAverageWaitingTime(){

    int size = 0;
    double totalTime = 0;
    double sojournTime;
    while(!eventQueue.isEmpty())
    {
      Data data = eventQueue.dequeue();
      totalTime += data.getDepartureTime() - data.getArrivalTime();
      size++;
    }

    sojournTime = totalTime/size;
    return sojournTime;

  }
  
  public double runSimulation(){

    while (currTime < totalSimTime)
    {

      if((timeForNextDeparture>timeForNextArrival)||buffer.isEmpty()) {
        e = Event.ARRIVAL;
      }

      else if(timeForNextDeparture<timeForNextArrival){
        e=Event.DEPARTURE;
      }

      if (e == Event.ARRIVAL)
      {
        currTime += timeForNextArrival;
        Data arrivingData = new Data();
        arrivingData.setArrivalTime(currTime);
        buffer.enqueue(arrivingData);
        timeForNextDeparture = timeForNextDeparture - timeForNextArrival;
        timeForNextArrival = getRandTime(arrivalRate);
      }

      else//(e == Event.DEPARTURE)
      {
        currTime += timeForNextDeparture;
        Data departingData = buffer.dequeue();
        departingData.setDepartureTime(currTime);
        eventQueue.enqueue(departingData);
        timeForNextArrival = timeForNextArrival - timeForNextDeparture;

        if (buffer.isEmpty())
        {
          timeForNextDeparture = timeForNextArrival + serviceTime;
        }
        else
        {
          timeForNextDeparture = serviceTime;
        }
      }
    }
    return calcAverageWaitingTime();
  }
}






