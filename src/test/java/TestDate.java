import cn.gx.entity.Time;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by guanxine on 16-4-19.
 */
public class TestDate {

    @org.junit.Test
    public void testDate() throws ParseException, JsonProcessingException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String start = "2016-12-15";
        String end ="2016-12-22";
        Date startDate = df.parse(start);
        Date endDate=df.parse(end);

        Time time=new Time();
        time.setStart(startDate);
        time.setEnd(endDate);


        ObjectMapper mapper = new ObjectMapper();

        String result = mapper.writeValueAsString(time);
        System.out.println(result);

        String s = mapper.writeValueAsString(start);
        System.out.println(s);
    }

    @org.junit.Test
    public void testStr2Date() throws IOException {
        String timeStr="{\"start\":\"2016-12-14\",\"end\":\"20160102\"}";

        ObjectMapper objectMapper=new ObjectMapper();
        Time time = objectMapper.readValue(timeStr, Time.class);

        System.out.println(time.toString());



    }
}
