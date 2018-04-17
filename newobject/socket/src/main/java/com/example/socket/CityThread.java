package com.example.socket;

/**
 * Created by wang on 2018/4/17.
 */
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public  class CityThread extends  Thread{
    @Override
    public void run() {
        // 1、创建HttpTransportSE对象，使用该对象完成WebService操作
        String transUrl
                = "http://www.webxml.com.cn/WebServices/"
                +"WeatherWebService.asmx";
        HttpTransportSE se = new HttpTransportSE(transUrl);
        // 2、创建SoapSerializationEnvelope对象
        SoapSerializationEnvelope envelope
                = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        // 执行WebService的类型（java\PHP\dotNet）
        envelope.dotNet = true;
        // 3、创建SoapObject对象，
        // 创建该对象是需要传入调用WebService的命名空间和相应方法名
        SoapObject request
                = new SoapObject("http://WebXml.com.cn/",
                "getSupportCity");
        // 执行调用方法的参数
        request.addProperty("byProvinceName", "山东");
        // 将前面两部创建的SoapObject对象设置为SoapSerializationEnvelope
        // 的传出SOAP消息体
        envelope.setOutputSoapObject(request);
        //envelope.bodyOut = request;

        try {
            //VER12版本第一个参数为null，之前版本传 namespace+方法名
            // SoapSerializationEnvelope对象作为调用远程WenSercier的参数
            se.call(null, envelope);
            // 调用完call方法之后，通过SoapSerializationEnvelope对象的bodyin
            // 获取返回信息，返回一个SoapObject对象，这个对象就是返回消息的
            // 载体，解析这个对象就可以获取返回的数据
            SoapObject respones = (SoapObject) envelope.bodyIn;
            SoapObject o = (SoapObject) respones.getProperty(0);
            for(int i=0; i<o.getPropertyCount(); ++i){
                Log.e("test", o.getPropertyAsString(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}

