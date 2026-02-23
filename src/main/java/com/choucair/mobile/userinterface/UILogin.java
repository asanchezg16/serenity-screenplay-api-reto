package com.choucair.mobile.userinterface;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class UILogin {

    public static final Target USERNAME_TXT = Target.the("Campo de texto username")
            .locatedForAndroid(AppiumBy.xpath("//android.widget.EditText[@text='Username']"))
            .locatedForIOS(AppiumBy.xpath("//XCUIElementTypeTextField[@value='Username']"));

    public static final Target PASSWORD_TXT= Target.the("Campo de texto para el password")
            .locatedForAndroid(AppiumBy.xpath("//android.widget.EditText[@content-desc='test-Password']"))
            .locatedForIOS(AppiumBy.xpath("//XCUIElementTypeSecureTextField[@value='Password']"));

    public static final Target LOGIN_BTN= Target.the("Botón para ingresar")
            .locatedForAndroid(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN']"))
            .locatedForIOS(AppiumBy.xpath("//XCUIElementTypeOther[@label='LOGIN']"));

    public static final Target LOGIN_SUCCESSFULL_TXT= Target.the("Logeo Exitoso")
            .locatedForAndroid(AppiumBy.xpath("//android.widget.TextView[@text='PRODUCTS']"))
            .locatedForIOS(AppiumBy.xpath("//XCUIElementTypeStaticText[@name='PRODUCTS']"));

}
