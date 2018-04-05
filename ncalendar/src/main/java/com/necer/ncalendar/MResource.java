package com.necer.ncalendar;

import android.content.Context;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class MResource{
        public static int getIdByName(Context context, String className, String name) {
    String packageName = context.getPackageName();
    Class r = null;
    int id = 0;
    try {
        r = Class.forName(packageName + ".R");

        Class[] classes = r.getClasses();
        Class desireClass = null;

        for (int i = 0; i < classes.length; ++i) {
            if (classes[i].getName().split("\\$")[1].equals(className)) {
                desireClass = classes[i];
                break;
            }
        }

        if (desireClass != null)
            id = desireClass.getField(name).getInt(desireClass);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
    } catch (SecurityException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (NoSuchFieldException e) {
        e.printStackTrace();
    }

    return id;
}
}