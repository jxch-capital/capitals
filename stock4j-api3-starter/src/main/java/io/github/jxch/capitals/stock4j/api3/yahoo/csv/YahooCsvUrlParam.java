package io.github.jxch.capitals.stock4j.api3.yahoo.csv;

import io.github.jxch.capitals.crawler.common.UrlParamSupport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.HttpUrl;

import java.util.Calendar;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YahooCsvUrlParam implements UrlParamSupport {
    private String code;
    private Date start;
    @Builder.Default
    private Date end = Calendar.getInstance().getTime();
    @Builder.Default
    private String interval = "1d";
    @Builder.Default
    private String events = "history";
    @Builder.Default
    private boolean includeAdjustedClose = true;

    public String getPeriod1Param() {
        return String.valueOf(this.start.getTime() / 1000);
    }

    public String getPeriod2Param() {
        return String.valueOf(this.end.getTime() / 1000);
    }

    public String getIntervalParam() {
        return this.interval;
    }

    public String getEventsParam() {
        return this.events;
    }

    public String getIncludeAdjustedCloseParam() {
        return String.valueOf(this.includeAdjustedClose);
    }

    @Override
    public HttpUrl toUrl(HttpUrl.Builder builder) {
        return builder
                .addPathSegments(getCode())
                .addQueryParameter("period1", getPeriod1Param())
                .addQueryParameter("period2", getPeriod2Param())
                .addQueryParameter("interval", getIntervalParam())
                .addQueryParameter("events", getEventsParam())
                .addQueryParameter("includeAdjustedClose", getIncludeAdjustedCloseParam())
                .build();
    }
}
