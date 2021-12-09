package uk.ac.reigate.exceptions


class NoDataSuppliedException extends ApiException {
    
    public NoDataSuppliedException() {
        super("No Data Provided");
    }
}
