package com.tnc.app;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

public class Unpack {
    public static void main(String[] args) throws ISOException {
        // Initialize packager. in this example, I'm using
        // XML packager. We also can use Java Code Packager
        // This code throws ISOException
        GenericPackager packager = new GenericPackager("packager/iso87ascii.xml");

        // Setting packager
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);

        // this is ISO8583 Message that we will Unpack
        //String isoMessage = "0200F23A440128A19000000000001600000A166034391330218389391000000150000000090116442884571623442809010902601102103028346034391330218389=3204000000000000000000000289800028037NICOMAS                               ID076                                                                            36035BFE9B658C7B92D0302800156433010006405340012003002";
        //String isoMessage = "0800822000000000000004000000000000000120104241020798301";
        String isoMessage = "081082200000020000000400000000000000012017424102079800301";

        // first, we must convert ISO8583 Message String to byte[]
        byte[] bIsoMessage = new byte[isoMessage.length()];
        for (int i = 0; i < bIsoMessage.length; i++) {
            bIsoMessage[i] = (byte) (int) isoMessage.charAt(i);
        }

        // second, we unpack the message
        isoMsg.unpack(bIsoMessage);

        // last, print the unpacked ISO8583
        System.out.println("MTI='" + isoMsg.getMTI() + "'");
        for (int i = 1; i <= isoMsg.getMaxField(); i++) {
            if (isoMsg.hasField(i))
                System.out.println(i + "='" + isoMsg.getString(i) + "'");
        }
    }

}
