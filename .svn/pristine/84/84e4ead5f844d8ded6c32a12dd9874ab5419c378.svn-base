package com.bxt.loginsert.util;

/*   The code below For Little Endian CPU ( X86, ARM, etc)
 *
 *   | des id (4byte) | src id (4byte) | message length (4byte) | message msgID (4byte) |
 *
 */
@SuppressWarnings("all")
public class MsgFormat {

    public static final int FIX_MSG_HEADER = 16;

    public MsgFormat() {

    }

    public static byte[] setBuf(int msgID, byte[] input, int len) {

        byte[] data = new byte[len + FIX_MSG_HEADER];

        //  content len

        data[8] = (byte)(len & 0xFF);
        data[9] = (byte)((len & 0xFF00) >> 8);
        data[10] = (byte)((len & 0xFF0000) >> 16);
        data[11] = (byte)((len & 0xFF000000) >> 24);

        // msg ID
        data[12] = (byte)(msgID & 0xFF);
        data[13] = (byte)((msgID & 0xFF00) >> 8);;
        data[14] = (byte)((msgID & 0xFF0000) >> 16);
        data[15] = (byte)((msgID & 0xFF000000) >> 24);

        if (len > 0)
        {
            System.arraycopy(input, 0, data, FIX_MSG_HEADER, len);
        }
        return data;
    }

    /*
     * | srcID(2byte) | src Index(2byte) | desID(2byte) | desIndex(2byte)| content length(4byte) | msgID(4byte) |  content (nbytes) |
     */

    public static byte[] setBufEx(int srcID, int srcIndex,
                                  int desID, int desIndex,
                                  int msgID,
                                  byte[] content,
                                  int len) {


        byte[] data = new byte[len + FIX_MSG_HEADER];

        data[0] = (byte)(srcID & 0xFF);
        data[1] = (byte)((srcID & 0xFF00) >> 8);

        data[2] = (byte)(srcIndex & 0xFF);
        data[3] = (byte)((srcIndex & 0xFF00) >> 8);

        data[4] = (byte)(desID & 0xFF);
        data[5] = (byte)((desID & 0xFF00) >> 8);
        data[6] = (byte)(desIndex & 0xFF);
        data[7] = (byte)((desIndex & 0xFF00) >> 8);

        // content len

        data[8] = (byte)(len & 0xFF);
        data[9] = (byte)((len & 0xFF00) >> 8);
        data[10] = (byte)((len & 0xFF0000) >> 16);
        data[11] = (byte)((len & 0xFF000000) >> 24);

        // msg ID
        data[12] = (byte)(msgID & 0xFF);
        data[13] = (byte)((msgID & 0xFF00) >> 8);;
        data[14] = (byte)((msgID & 0xFF0000) >> 16);
        data[15] = (byte)((msgID & 0xFF000000) >> 24);

        if (len > 0)
        {
            System.arraycopy(content, 0, data, FIX_MSG_HEADER, len);
        }

        return data;
    }

    public static int getMsgID (byte[] msg){

        int msgID = msg[12] & 0XFF
                | (msg[13] << 8) & 0XFF00
                | (msg[14] << 16) & 0XFF0000
                | (msg[15] << 24) & 0XFF000000;

        return msgID;
    }

    public static int getLen (byte[] msg) {
        int msgLen = msg[8] & 0XFF
                | (msg[9] << 8) & 0XFF00
                | (msg[10] << 16)& 0XFF0000
                | (msg[11] << 24)& 0XFF000000;
        return msgLen;
    }

    public static byte[] getContent(byte[] msg) {
        int len = getLen(msg);

        byte[] buffer = new byte[len];

        System.arraycopy(msg, FIX_MSG_HEADER, buffer, 0, len);
        return buffer;
    }

    public static int getSrc(byte[] msg) {
        return (msg[0] & 0xFF | (msg[1] << 8) & 0xFF00 | msg[2] & 0xFF0000 | msg[3] & 0xFF000000);
    }

    public static int getDes(byte[] msg){
        return (msg[4] & 0xFF | (msg[5] << 8) & 0xFF00 | msg[6] & 0xFF0000 | msg[7] & 0xFF000000);
    }

    public static int getSrcEx(byte[] msg) {
        return (msg[0] & 0xFF | (msg[1] << 8) & 0xFF00);
    }

    public static int getSrcIndexEx(byte[] msg) {
        return (msg[2] & 0xFF | (msg[3] << 8) & 0xFF00);
    }

    public static int getDesEx(byte[] msg) {
        return (msg[4] & 0xFF | (msg[5] << 8) & 0xFF00);
    }

    public static int getDesIndexEx(byte[] msg) {
        return (msg[6] & 0xFF | (msg[7] << 8) & 0xFF00);
    }


    public static void writeFileSetBufEx(byte[] tmpMsg) {
        long timestamp = System.currentTimeMillis();
        tmpMsg[0] = (byte) (timestamp & 0xFF);
        tmpMsg[1] = (byte) ((timestamp & 0xFF00) >> 8);
        ;
        tmpMsg[2] = (byte) ((timestamp & 0xFF0000) >> 16);
        tmpMsg[3] = (byte) ((timestamp & 0xFF000000) >> 24);
        tmpMsg[4] = (byte) ((timestamp & 0xFF00000000L) >> 32);
        tmpMsg[5] = (byte) ((timestamp & 0xFF0000000000L) >> 40);
        tmpMsg[6] = (byte) ((timestamp & 0xFF000000000000L) >> 48);
        tmpMsg[7] = (byte) ((timestamp & 0xFF00000000000000L) >> 56);
    }
}
