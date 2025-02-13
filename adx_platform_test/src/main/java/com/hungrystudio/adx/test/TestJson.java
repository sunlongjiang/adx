package com.hungrystudio.adx.test;

import com.alibaba.fastjson.JSONObject;
import com.hungrystudio.adx.operator.S3ForGeoedgeFlatMap;
import com.hungrystudio.adx.util.CommonAnalysisUtils;

public class TestJson {


    public static void main(String[] args) {
        String valueByte = "{\"ext\":\"{\\\"adType\\\":\\\"2\\\",\\\"adm\\\":\\\"\\\\u001f\\\\ufffd\\\\u0008\\\\u0000\\\\u0000\\\\u0000\\\\u0000\\\\u0000\\\\u0000\\\\ufffd\\\\ufffdV[o\\\\ufffdH\\\\ufffd\\\\ufffd+\\\\ufffdx8Oq\\\\u0002\\\\ufffd$q\\\\ufffd\\\\ufffdѪ\\\\u0002l\\\\u0008\\\\u0005\\\\ufffd\\\\u0006\\\\ufffdpԲ\\\\u0000\\\\ufffd\\\\\\\\}\\\\u0005\\\\ufffd\\\\u001c\u007F\\\\ufffd\\\\ufffd\\\\ufffdQ\\\\ufffd\\\\ufffdtzf4#\\\\ufffdS?\\\\ufffd\\\\u0012\\\\ufffd\\\\ufffdj\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdEy\\\\ufffd\\\\ufffd6Q\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdO\\\\ufffd\\\\ufffd\\\\u003cH/\\\\ufffdr\\\\ufffdHwRT\\\\ufffd˦\\\\ufffd^\\\\ufffd倫/\\\\ufffd\\\\u0017\\\\ufffdNj\\\\ufffd\\\\ufffd\\\\ufffd.\\\\ufffd\\\\ufffd\\\\u001b\\\\ufffd\\\\ufffd'\\\\ufffdI\\\\ufffd\\\\ufffd\\\\ufffdH/\\\\ufffd}\\\\u0005hyb\\\\ufffdel\\\\ufffd6\\\\ufffd[\\\\ufffdh\\\\ufffd\\\\ufffd\\\\ufffdg\\\\ufffdםtX\\\\ufffd\\\\ufffd\\\\ufffda)@~\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdu*@\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd}ܛ\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdx\\\\ufffd\\\\ufffd\\\\u0017)k\\\\ufffd]\\\\ufffd\\\\ufffd\\\\ufffdP\\\\u001fw\\\\ufffd\\\\ufffd\\\\ufffd\\\\u003e߬\\\\ufffdq\\\\ufffd,6\\\\ufffd\\\\ufffde\\\\ufffdP\\\\ufffd\\\\ufffd[\\\\ufffd\\\\ufffdvѺ\\\\u0011}\\\\ufffd\\\\ufffdhY\\\\ufffdt\\\\ufffdRz\\\\ufffdY\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdM*\\\\u0008\\\\u0008\\\\ufffd\\\\ufffd\\\\ufffd\\\\u003c\\\\ufffd\\\\ufffd\\\\ufffdh\u007F\\\\ufffdh\u007F\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd4n\\\\ufffd\\\\ufffd7˺\\\\ufffd\\\\ufffd+\\\\ufffd\\\\ufffd?5b\\\\u00115\\\\ufffd`pKپ\\\\ufffdNQu\\\\\\\\J/ҭ\\\\ufffd?n\\\\ufffd\\\\ufffdC@\\\\ufffdh\\\\ufffd\\\\u0018\\\\u001f\\\\ufffd\\\\ufffd\\\\u0006|\\\\ufffdu\\\\ufffd\\\\u0003\\\\ufffd\\\\ufffd\\\\u0005\\\\ufffdVyR\\\\ufffd\\\\u0018.\\\\u000f\\\\ufffdѽ\\\\ufffdF\\\\ufffd~'U\\\\ufffd\\\\ufffd\\\\u0014щX\\\\ufffd\\\\u001c\\\\ufffd\\\\ufffd\\\\\\\\\\\\u001e\\\\ufffd\\\\ufffd\u007F\\\\ufffd i-\\\\ufffd\\\\ufffd=\\\\ufffd\\\\ufffd\\\\ufffd}\\\\ufffd]?$\\\\ufffdQ\\\\ufffd~\\\\u0018\\\\ufffd\\\\ufffd\u007F2\\\\ufffd]\\\\ufffd7\\\\ufffdI\\\\ufffd\\\\u0019Ȧ{\\\\ufffd\\\\ufffddJ\\\\ufffd\\\\ufffdV\\\\ufffdN\\\\ufffd\\\\ufffd\\\\u001b\\\\\\\\\\\\ufffd\\\\ufffdyUΨ,\\\\ufffdjٳ\\\\ufffd\\\\ufffd=,\\\\ufffd\\\\ufffd]\\\\ufffdwG]TA[\\\\ufffd\\\\ufffdA\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdQ:A6MI\\\\ufffdS\\\\ufffdŖl\\\\ufffdU\\\\ufffd1\\\\ufffd \\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\u0015\\\\ufffd4\\\\ufffdzFo\\\\ufffd\\\\ufffd\\\\u001b6\\\\ufffd\\\\ufffdQ;\\\\ufffd\\\\u0005me\\\\ufffd\\\\u000c̝=ە\\\\u0013\\\\ufffdl\\\\u0007\\\\ufffd=Ye1;mk:$|\\\\ufffd2\\\\ufffd\\\\ufffd#\\\\ufffd6\\\\ufffd\\\\ufffd\\\\u001c\\\\ufffdh5\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd~\\\\ufffd;?3\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdq\\\\ufffdq݄\\\\ufffd\\\\ufffd\\\\u0018\\\\u0012#$.\\\\ufffd\\\\u0017\\\\ufffd\\\\ufffdB\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdW\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd?g\\\\ufffdL\\\\ufffd\\\\ufffd5\\\\ufffd\\\\ufffdk\\\\ufffd\\\\ufffdsr\\\\ufffdsr\\\\ufffds\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdӞ\\\\ufffd\\\\ufffd+'\\\\ufffd^\\\\u0026\\\\ufffd\\\\ufffdL\\\\ufffd{\\\\ufffd\\\\ufffd\\\\u001fj\\\\ufffd[2)\\\\ufffd\\\\ufffdT\\\\ufffd\\\\u0026U]\\\\ufffdu]\\\\ufffd\\\\ufffdq\\\\ufffd\\\\ufffd9\\\\ufffd0N\\\\ufffd\\\\ufffd\\\\ufffd\\\\u001d㤮9yg\\\\ufffd(5'\\\\ufffdZ\\\\ufffd\\\\ufffd\\\\u0012\\\\ufffdadݔ\\\\ufffd\\\\ufffd\\\\u0018$=mIoWҮ\\\\ufffd\\\\ufffd\\\\ufffdqK\\\\ufffdF\\\\ufffdV\\\\ufffdL\\\\ufffd־\\\\ufffd\\\\u001d\\\\r\\\\ufffdp\\\\u000c\\\\ufffdt\\\\u000c\\\\ufffd:\\\\u001a\\\\ufffd\\\\ufffd1̍c\\\\ufffdۣa\uE3C6\\\\ufffd\\\\u001c\\\\r\\\\ufffd;\\\\ufffd\\\\ufffd8\\\\ufffd4FY\\\\ufffd\\\\u0002\\\\ufffdr\\\\u0010Q\\\\ufffd\\\\ufffd\\\\ufffdݠ`\\\\ufffdp\\\\u001a\\\\u0016\\\\ufffd:Y\\\\ufffd˨d\\\\ufffd\\\\ufffd\\\\u001bz\\\\u0001g\\\\u0017\\\\ufffd\\\\u0013\\\\\\\\\\\\u0012n\\\\u0017\\\\ufffd\\\\ufffd֚LS\\\\ufffd[\\\\ufffd0\\\\u000b\\\\ufffdϯ@\\\\ufffd1\\\\u001c\\\\ufffdo\\\\u001c\\\\u001a`\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd@䘶\\\\ufffd4\\\\ufffd\\\\ufffd\\\\u0011\\\\ufffd\\\\ufffdԀC\\\\u0012\\\\r\\\\ufffd\\\\ufffd\\\\ufffd\u007F\\\\ufffdw\\\\ufffd\\\\ufffd\\\\ufffd-\\\\ufffd\\\\ufffd\uD800\uDF5E@\\\\ufffd\\\\u0002\\\\ufffd\\\\u0001n\\\\ufffd\\\\ufffd\\\\ufffde\\\\ufffdc(\\\\u001e\\\\ufffd\\\\ufffd\\\\u001e\\\\u0019\\\\ufffdr\\\\ufffd?\\\\u0012ʺ\\\\ufffd#\\\\u0005u\\\\ufffd@\\\\ufffd\\\\\\\"7\\\\u0005,\\\\ufffd7\\\\ufffd=u\\\\ufffd\\\\ufffd\\\\ufffdn\\\\ufffd\\\\u0019?G\\\\ufffd\\\\ufffdG\\\\ufffd\\\\ufffd\\\\u0026V\\\\ufffdZ\\\\ufffdm\\\\ufffd\\\\ufffd\\\\ufffd_.\\\\ufffd\\\\ufffdV9#\\\\ufffdBKA\\\\ufffd\\\\ufffda\\\\ufffd\\\\\\\\\\\\ufffd\\\\ufffdPMdv\\\\ufffd\\\\u000b\\\\ufffd\\\\ufffdl_\\\\ufffd\\\\ufffdV!\\\\ufffd\\\\ufffdY9\\\\ufffd^2\\\\u000f\\\\u003c\\\\ufffd\\\\ufffd\\\\ufffdy\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\u0001\\\\u0012\\\\ufffd\\\\u0018\\\\ufffdr̮\\\\ufffd\\\\n\\\\ufffd\\\\ufffd\\\\u0014\\\\ufffd\\\\ufffd\\\\u003epAAj\\\\ufffd\\\\u0014\\\\t \\\\ufffde\\\\ufffd\\\\u0014)\\\\\\\\\\\\u0003\\\\ufffd\\\\ufffd\\\\ufffd.h\\\\ufffd\\\\ufffdK\\\\ufffd,\\\\ufffd\\\\u0002\\\\ufffd\\\\u001f\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdV\\\\ufffd\\\\ufffd ˓\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffde1\\\\u001d\\\\ufffd\\\\ufffd\\\\ufffd?\\\\u0000\\\\ufffdb\\\\ufffd\\\\u0000\\\\ufffd\\\\ufffd,I\\\\ufffd\\\\u001a\\\\ufffd\\\\u0006\\\\ufffd2\\\\ufffd\\\\u0003\\\\u0008\\\\ufffd\\\\ufffd\\\\u0014\\\\u0017\\\\ufffd\\\\ufffd\\\\ufffd^\\\\ufffdQٸ{\\\\u0026)\\\\u0014\\\\ufffd\\\\ufffdP\\\\u0003P\\\\ufffd\\\\ufffd\\\\ufffd\\\\u0018\\\\u000c\\\\ufffd\\\\ufffd_,\\\\ufffd\\\\ufffdv\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd8\\\\ufffd2?\\\\ufffd\\\\ufffd0)\\\\ufffd\\\\ufffd\\\\ufffdk\\\\ufffd\\\\ufffd\\\\u001a\\\\ufffd\\\\u0016\\\\u0010\\\\ufffd\\\\ufffdu\\\\u000fLՕ\\\\ufffd\\\\ufffd˝\\\\u0001{\\\\ufffd\\\\u003c\\\\ufffd\\\\u0017\\\\u0014h\\\\ufffd\\\\ufffd\\\\ufffds\\\\ufffdr\\\\ufffd\\\\ufffd\\\\u0003\\\\ufffd\\\\ufffdX\\\\ufffd\\\\u0015i'\\\\ufffd\\\\ufffdYP\\\\ufffd\\\\ufffd\\\\u000c\\\\u0006ƀ\\\\n\\\\ufffd\\\\u0014\\\\u0018L`8\\\\u001bR\\\\u0007\\\\ufffd\\\\ufffd\\\\u0018\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd}\\\\ufffd\\\\ufffd\\\\ufffd}\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdb\\\\ufffd\u007F\\\\u000f\\\\ufffd\\\\ufffd,\\\\u0019\\\\ufffd\\\\ufffd\\\\ufffd\\\\u001eU\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdl\\\\u001eM\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\u0007\\\\u0006\\\\ufffdKi\\\\ufffd)\\\\ufffd[\\\\u001f\\\\ufffd\\\\u0012\\\\ufffd6\u007F\\\\ufffd]\\\\u001f\\\\ufffd\\\\u001c\\\\ufffd\\\\u0026\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd̢i\\\\ufffd\\\\ufffdt\\\\u0026e\\\\ufffdn\\\\u0014pP\\\\u0003\\\\u0001H\\\\ufffd\\\\ufffdO\\\\ufffd\\\\ufffd\\\\ufffd\uF5AE\\\\u0005\\\\ufffdb\\\\ufffd\\\\ufffd\\\\u000b\\\\ufffdu9\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdj싫g{\\\\ufffdi\\\\ufffd\\\\u0014\\\\ufffdx\\\\ufffd\\\\ufffdzJ\\\\ufffd\\\\u001a\\\\ufffd\\\\ufffdK\\\\u0001\\\\ufffdF\\\\ufffd\\\\ufffdz=k\\\\u0018\\\\ufffd\\\\ufffd\\\\u0018ܘ\\\\ufffd\\\\ufffdܫ\\\\u001a\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd:h\\\\ufffdJX09TY\\\\ufffd\\\\u0019\\\\ufffdm\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdf\\\\u001d\\\\ufffd\\\\u0006=[53\\\\u001b\\\\u0006th*\\\\ufffdO\\\\ufffd1#.\\\\ufffd\\\\u0000\\\\u0002\\\\ufffd\\\\u0004\\\\ufffd\\\\ufffdF8\\\\ufffd\\\\u0012`\\\\u0018r\\\\ufffd+CȨ\\\\ufffd\\\\ufffd\\\\u000f\\\\u0015T\\\\ufffd!6\\\\ufffdڴ\\\\ufffd\\\\u0012\\\\u000f\\\\ufffd\\\\u0005z\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd/O\\\\ufffdD\\\\ufffd\\\\ufffd#\\\\ufffdoz\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdʗug\\\\ufffd\\\\ufffdoS\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdE\\\\ufffd\\\\u001a\\\\u0008`\\\\ufffd\\\\u0010\\\\ufffd\\\\u003e^{\\\\ufffdj\\\\ufffdO\\\\ufffdC\\\\ufffd}\\\\ufffdI\\\\u0019\\\\u0014\\\\ufffdO\\\\ufffdZr\\\\u001d\\\\ufffdq\\\\u001e\\\\u0004B\\\\ufffd:I\\\\ufffdd\\\\ufffdkY\\\\u0000\\\\ufffd\\\\ufffd\\\\ufffdv\\\\ufffd2\\\\u0014\\\\u0001'\\\\ufffdh\\\\ufffd\\\\ufffdj͵}\\\\ufffd\\\\ufffd=2Ѫ\\\\ufffdhğ\\\\ufffd$\\\\u0002\u007F\\\\ufffdᖽvz\\\\ufffd3\\\\ufffdw\\\\ufffd\\\\ufffdo\\\\ufffd:\\\\ufffdb\\\\ufffdqL16\\\\u0000u\\\\ufffd\\\\ufffd\\\\ufffdzD\\\\u0010\\\\ufffd4(\\\\u0018\\\\ufffdKNC5\\\\ufffda\\\\ufffdӰ\\\\ufffdix`\\\\u0008/\\\\ufffd\\\\ufffdi\\\\ufffd2D\\\\ufffd\\\\ufffd;\\\\ufffd\\\\u003c\\\\ufffd(\\\\ufffd\\\\u0011\\\\u001dr\\\\u001a[\\\\u000cq\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdF\\\\ufffd\\\\ufffd4\\\\ufffdj$\\\\u0005\\\\ufffd\\\\\\\"fX\\\\u003c1,\\\\ufffd\\\\u001c\\\\ufffd\\\\ufffd\\\\u001a\\\\ufffd\\\\ufffd\\\\u001a\\\\ufffdq\\\\ufffd\\\\ufffd\\\\ufffd\\\\u001e\\\\ufffd\\\\ufffd\\\\u0002A\\\\ufffdG\\\\ufffd\\\\ufffd \\\\ufffd,\\\\ufffdや\\\\ufffd\\\\ufffd0\\\\ufffd\\\\u0010\\\\u001eB\\\\ufffd\\\\u0007\\\\u000bQ\\\\ufffd \\\\ufffd7\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\u0010\\\\ufffd\\\\ufffd',b\\\\u000fKK\\\\ufffd\\\\ufffd:\\\\ufffd1\\\\nqz=`\\\\ufffdx\\\\ufffd\\\\ufffd\\\\ufffdڍ;\\\\ufffdTKt\\\\u0017\\\\ufffd\\\\ufffd\\\\u000f\\\\u0018\\\\u003e\\\\ufffd,5D\\\\ufffd\\\\ufffdj\\\\ufffd\\\\u001dOK\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdN\\\\ufffdaU\\\\ufffd\\\\ufffd6\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\u003c\\\\ufffd,Z\\\\u0026\\\\u003cP\\\\ufffd?\\\\ufffd\\\\ufffd:\\\\u0014\\\\ufffd\\\\ufffd\\\\ufffd\u05F6\\\\ufffd\\\\ufffd\\\\ufffd%\\\\ufffd\\\\ufffdԕm5̜);\\\\ufffdE\\\\ufffd\\\\ufffdj\\\\ufffd\\\\u000b\\\\ufffdE\\\\u0015\\\\u0016n'(\\\\\\\\\\\\ufffd\\\\ufffd\u007F\\\\nh\\\\u0008\\\\ufffd*q\\\\ufffd\\\\ufffd\\\\ufffd\\\\t!\\\\ufffdX\\\\ufffd2\\\\u0007\\\\ufffd\\\\u0012\\\\u0007xX.\\\\ufffd\\\\ufffdq/\\\\ufffd\\\\ufffdo]\\\\ufffdV\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffdY\\\\ufffd\\\\ufffd\\\\u001f\\\\ufffd\\\\u0015\\\\ufffd_M\\\\ufffdա\\\\ufffdw\\\\ufffdʁ,\\\\ufffd\\\\ufffd\\\\ufffd\\\\u0005\\\\ufffd\\\\ufffd\\\\u0016\\\\ufffdۂ|[\\\\ufffdo\\\\u000b\\\\ufffdmA\\\\ufffd-ȷ\\\\u0005\\\\ufffd[[\\\\u0010\\\\ufffd\\\\u0003]S\\\\ufffdv\\\\ufffd\\\\u003c\\\\ufffd3=M\\\\u0026\\\\ufffd\\\\ufffdp\\\\ufffd\\\\u001c\\\\ufffd̖{\\\\ufffd[\\\\u0017\\\\u0000\\\\u000f\\\\ufffdQ\\\\ufffd\\\\u001d)+\\\\ufffdӢ(\\\\ufffd\\\\ufffd\\\\ufffd\\\\ufffd~\\\\ufffd\u007F\\\\u0000\\\\u0000\\\\u0000\\\\ufffd\\\\ufffd\\\\ufffd\\\\u0011\\\\ufffd\\\\u0007\\\\u0018\\\\u0013\\\\u0000\\\\u0000\\\",\\\"adomain\\\":\\\"advertiserDomain.sandbox.inmobi.com\\\",\\\"resp\\\":\\\"{\\\\\\\"status\\\\\\\":{\\\\\\\"code\\\\\\\":\\\\\\\"Success\\\\\\\",\\\\\\\"message\\\\\\\":\\\\\\\"Success\\\\\\\"},\\\\\\\"response\\\\\\\":{\\\\\\\"project_id\\\\\\\":\\\\\\\"552449dca1e1aa258e621a6554ff290f\\\\\\\",\\\\\\\"name\\\\\\\":\\\\\\\"adx:edge:inmobi:50b2dee0575071e481c48f51d8c9bdcb:1:CN:1736775659\\\\\\\",\\\\\\\"scan_now\\\\\\\":{\\\\\\\"status\\\\\\\":{\\\\\\\"code\\\\\\\":\\\\\\\"Success\\\\\\\",\\\\\\\"message\\\\\\\":\\\\\\\"Success\\\\\\\"}},\\\\\\\"Scans\\\\\\\":null}}\\\"}\",\"country\":\"CN\",\"ver\":\"1.0\",\"crid\":\"<adv>88b37efcd5f34d368e60317c706942a4<crid>7b3f48bc605d446a9c166ad69e7ef47b\",\"os\":\"android\",\"cst\":\"1735905894980\",\"pid\":\"34\",\"target_id\":\"6\",\"rid\":\"a0cba4e0-49a4-4d5c-85aa-c080d6c0347c\",\"project_name\":\"adx:edge:inmobi:50b2dee0575071e481c48f51d8c9bdcb:1:CN:1736775659\",\"sid\":\"c1391b4f-1e33-413e-9dda-9e248795b26f\",\"dsp_group_id\":\"inmobi\",\"project_id\":\"552449dca1e1aa258e621a6554ff290f\",\"bundle_id\":\"com.mathbrain.sudoku\",\"event_name\":\"adx_s_geoedge_request\",\"p_type\":\"1\",\"ver_n\":\"1\",\"sdk_ver\":\"1.0.0\",\"timestamp\":\"1736775659\"}";
        JSONObject analysis = CommonAnalysisUtils.analysisToJSON(valueByte);
        S3ForGeoedgeFlatMap test = new S3ForGeoedgeFlatMap(new JSONObject());
        test.getJson(analysis);
    }
}
