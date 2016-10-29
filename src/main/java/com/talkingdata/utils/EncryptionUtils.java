package com.talkingdata.utils;

import java.security.MessageDigest;

/**
 * User��    ysl
 * Date:   2016/10/18
 * Time:   17:15
 */
public class EncryptionUtils {


    /**
     * ͨ��MD5�����㷨���м���
     * @param source        ��Ҫ���ܵ��ֽ�
     * @return              �����ؼ��ܺ���ַ���
     * @throws Exception
     */
    public static String getMD5(byte[] source)throws Exception
    {
        String s = null;
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };// �������ֽ�ת����16���Ʊ�ʾ���ַ�
            MessageDigest md = MessageDigest
                    .getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();// MD5 �ļ�������һ�� 128 λ�ĳ�������
            // ���ֽڱ�ʾ���� 16 ���ֽ�
            char str[] = new char[16 * 2];// ÿ���ֽ��� 16 ���Ʊ�ʾ�Ļ���ʹ�������ַ��� ���Ա�ʾ�� 16
            // ������Ҫ 32 ���ַ�
            int k = 0;// ��ʾת������ж�Ӧ���ַ�λ��
            for (int i = 0; i < 16; i++) {// �ӵ�һ���ֽڿ�ʼ���� MD5 ��ÿһ���ֽ�// ת���� 16
                // �����ַ���ת��
                byte byte0 = tmp[i];// ȡ�� i ���ֽ�
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];// ȡ�ֽ��и� 4 λ������ת��,// >>>
                // Ϊ�߼����ƣ�������λһ������
                str[k++] = hexDigits[byte0 & 0xf];// ȡ�ֽ��е� 4 λ������ת��

            }
            s = new String(str);// ����Ľ��ת��Ϊ�ַ���
        return s;
    }


    /**
     * ͨ��SHA1�����㷨���м���
     * @param decript           ��Ҫ���ܵ������ַ���
     * @return                     �����ؼ��ܺ�������ַ���U��
     * @throws Exception
     */
    public static String getSHA1(String decript)throws Exception
    {
        MessageDigest digest = MessageDigest
                .getInstance("SHA-1");
        digest.update(decript.getBytes());
        byte messageDigest[] = digest.digest();
        // Create Hex String
        StringBuffer hexString = new StringBuffer();
        // �ֽ�����ת��Ϊ ʮ������ ��
        for (int i = 0; i < messageDigest.length; i++)
        {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString();
    }



}
