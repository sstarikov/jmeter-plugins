package kg.apc.jmeter.threads;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.jmeter.gui.util.PowerTableModel;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.NullProperty;
import org.apache.jmeter.testelement.property.StringProperty;
import org.apache.jmeter.threads.AbstractThreadGroup;
import org.apache.jmeter.threads.JMeterThread;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

public class UltimateThreadGroup
      extends AbstractThreadGroup
      implements Serializable
{
   //private static final Logger log = LoggingManager.getLoggerForClass();
   public static final String DATA_PROPERTY = "ultimatethreadgroupdata";

   public UltimateThreadGroup()
   {
      super();
   }

   public static CollectionProperty tableModelToCollectionProperty(PowerTableModel model)
   {
      CollectionProperty rows = new CollectionProperty(UltimateThreadGroup.DATA_PROPERTY, new ArrayList<Object>());
      for (int col = 0; col < model.getColumnCount(); col++)
      {
         rows.addItem(model.getColumnData(model.getColumnName(col)));
      }
      return rows;
   }

   // FIXME: too inefficient
   public void scheduleThread(JMeterThread thread)
   {
      ThreadScheduleParams params = new ThreadScheduleParams(thread, getData());

      long ascentPoint = System.currentTimeMillis() + 1000 * params.initialDelay;
      final int rampUpDelayForThread = (int) Math.floor(1000 * params.startRampUp * (double) params.threadSeqNum / params.numThreads);
      long startTime = ascentPoint + rampUpDelayForThread;
      long descentPoint = startTime + 1000 * params.flightTime + 1000 * params.startRampUp - rampUpDelayForThread;

      thread.setStartTime(startTime);
      thread.setEndTime(descentPoint+(int) Math.floor(1000 * params.endRampUp * (double) params.threadSeqNum / params.numThreads));

      thread.setScheduled(true);
   }

   public JMeterProperty getData()
   {
      //log.info("getData");
      JMeterProperty prop = getProperty(DATA_PROPERTY);
      return prop;
   }

   void setData(CollectionProperty rows)
   {
      //log.info("setData");
      setProperty(rows);
   }

   @Override
   public int getNumThreads()
   {
      int result = 0;

      JMeterProperty threadValues = getData();
      if (!(threadValues instanceof NullProperty))
      {
         CollectionProperty columns = (CollectionProperty) threadValues;
         List<?> col = (List<?>) columns.get(0).getObjectValue();
         Iterator<?> iter = col.iterator();
         while (iter.hasNext())
         {
            StringProperty prop = (StringProperty) iter.next();
            result += prop.getIntValue();
         }
      }

      return result;
   }
}