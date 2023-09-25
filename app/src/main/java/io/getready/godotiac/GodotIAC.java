package io.getready.godotiac;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class GodotIAC extends GodotPlugin {

    private final Godot godot;

    public GodotIAC(Godot godot) {
        super(godot);
        this.godot = godot;
    }

    @NonNull
    @Override
    public String getPluginName() {
        return "GodotIAC";
    }

    @NonNull
    @Override
    public List<String> getPluginMethods() {
        return Arrays.asList("start", "stop", "deeplink_received");
    }

    @NonNull
    @Override
    public Set<SignalInfo> getPluginSignals() {
        Set<SignalInfo> signals = new ArraySet<>();
        signals.add(new SignalInfo("deeplink_received", String.class, String.class));
        return signals;
    }

    public void start() {
        Activity godotActivity = godot.getActivity();
        IAC.getInstance().start(godotActivity, (payload, origin) -> {
            emitSignal("deeplink_received", payload, origin);
        });
    }

    public void stop() {
        IAC.getInstance().stop();
    }
}
