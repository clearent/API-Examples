package sample.domain.settings;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class CscFilters {
    @SerializedName("response-filters")
    ResponseFilters responsefilters = new ResponseFilters();
    public class ResponseFilters {
        @SerializedName("response-filter")
        ArrayList<ResponseFilter.Filter> filters = new ResponseFilter().setResponseFilter();

        public class ResponseFilter {
            ArrayList<Filter> responseFilter = new ArrayList<>();

            private ArrayList<Filter> setResponseFilter() {
                Filter xFilter = new Filter();
                xFilter.setName("CSC_MATCHED");
                xFilter.setAllowed(true);
                responseFilter.add(xFilter);
                return responseFilter;
            }

            private class Filter {

                private void setName(String name) {
                    this.name = name;
                }

                private void setAllowed(Boolean allowed) {
                    this.allowed = allowed;
                }

                private String name;
                private Boolean allowed;


            }
        }
    }
}
