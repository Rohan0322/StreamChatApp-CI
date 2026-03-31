package com.streamchatreactnative;

import androidx.annotation.Nullable;
import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StreamChatReactNativePackage extends TurboReactPackage {

  @Nullable
  @Override
  public NativeModule getModule(String name, ReactApplicationContext reactContext) {
    if (name.equals(StreamChatReactNativeModule.NAME)) {
      return new StreamChatReactNativeModule(reactContext);
    } else {
      return null;
    }
  }

  @Override
  public ReactModuleInfoProvider getReactModuleInfoProvider() {
    return () -> {
      final Map<String, ReactModuleInfo> moduleInfos = new HashMap<>();
      boolean isTurboModule = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
      moduleInfos.put(
        StreamChatReactNativeModule.NAME,
        new ReactModuleInfo(
          StreamChatReactNativeModule.NAME,
          StreamChatReactNativeModule.NAME,
          false, // canOverrideExistingModule
          false, // needsEagerInit
          true, // hasConstants
          false, // isCxxModule
          isTurboModule // isTurboModule
        )
      );
      return moduleInfos;
    };
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    // StreamShimmerViewManager only exists/compiles with New Architecture.
    // We must avoid referencing it directly, otherwise javac fails even if the branch is never executed.
    if (!BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
      return Collections.emptyList();
    }

    try {
      Class<?> clazz =
        Class.forName("com.streamchatreactnative.shared.StreamShimmerViewManager");
      Object instance = clazz.getDeclaredConstructor().newInstance();
      @SuppressWarnings("unchecked")
      ViewManager viewManager = (ViewManager) instance;
      return Collections.<ViewManager>singletonList(viewManager);
    } catch (Throwable t) {
      // If something goes wrong (class missing, reflection blocked, etc.), don't crash the build/app.
      return Collections.emptyList();
    }
  }
}
