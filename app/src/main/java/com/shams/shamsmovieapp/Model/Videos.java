
package com.shams.shamsmovieapp.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Videos {

    @SerializedName("results")
    @Expose
    private List<ResultDetails> results = null;

    public List<ResultDetails> getResults() {
        return results;
    }

    public void setResults(List<ResultDetails> results) {
        this.results = results;
    }

}
