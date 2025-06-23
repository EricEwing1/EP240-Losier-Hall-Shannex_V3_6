package main;

public interface Scaler {
    // Scales the inbound value to the internal representation.
    double inboundScale(double in);

    // Scales the internal representation to the external value.
    double outboundScale(double out);
}
