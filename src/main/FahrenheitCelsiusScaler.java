package main;

public class FahrenheitCelsiusScaler implements Scaler {
    // Scales the inbound (Fahrenheit) value to Celsius.
    public double inboundScale(double fahrenheit) {
        return (fahrenheit - 32.0) / 1.8;
    }

    // Scales Celsius to the external (Fahrenheit) value.
    public double outboundScale(double celsius) {
        return celsius * 1.8 + 32.0;
    }
}
