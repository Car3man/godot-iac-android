package io.getready.godotiac;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;

public class GodotIAC extends GodotPlugin {
    public GodotIAC(Godot godot) {
        super(godot);

        Activity godotActivity = godot.getActivity();
        IAC.getInstance().start(godotActivity, (payload, origin) -> {
            Toast toast = Toast.makeText(godotActivity, "App link received!", Toast.LENGTH_LONG);
            toast.show();
        });
    }

    @NonNull
    @Override
    public String getPluginName() {
        return "GodotIAC";
    }
}
