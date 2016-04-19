package cn.gx.util;

import cn.gx.exception.CustomDateRuntimeException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guanxine on 16-4-19.
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {

    private static String format="yyyy-MM-dd";
    private SimpleDateFormat formatter =
            new SimpleDateFormat(format);

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        String date = jsonparser.getText();

        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            String field = jsonparser.getParsingContext().getCurrentName();
            throw new CustomDateRuntimeException(field);
        }
    }
}
