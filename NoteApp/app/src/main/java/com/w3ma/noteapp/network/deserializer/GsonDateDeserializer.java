package com.w3ma.noteapp.network.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Emanuele on 22/07/2016.
 */
public class GsonDateDeserializer implements JsonDeserializer<Date> {

    private final List<DateFormat> dateFormatList;

    public GsonDateDeserializer() {
        dateFormatList = new LinkedList<>();
        dateFormatList.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())); //ex. 2015-01-25T20:20:30+01:00
        dateFormatList.add(new SimpleDateFormat("dd/MM/yyyy'T'HH:mm", Locale.getDefault())); //ex. 04/05/2016T16:45"
    }

    @Override
    public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
        Date date = null;
        final String dateString = json.getAsJsonPrimitive().getAsString();
        for (final Iterator<DateFormat> it = dateFormatList.iterator(); it.hasNext(); ) {
            final DateFormat dateFormat = it.next();
            try {
                date = dateFormat.parse(dateString);
                break;
            } catch (final ParseException e) {
                if (!it.hasNext()) {
                    throw new JsonParseException(e.getMessage(), e);
                }
            }
        }
        return date;
    }
}
