package org.anotes.services.translator.infrastructure.support;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: gmc
 * Date: 03/06/11
 */
public class SimpleHtmlParser {
    public static Pattern tagPattern = Pattern.compile("\\b?\\w+\\b?");

    public static void main(String[] args) {
        String str = "<html>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html\\; charset=UTF-8\">\n" +
                "<body><span class=\"systran_seg\" id=\"Sp1.s2_o\"><span class=\"systran_altmeaning\" value=\"&lt\\;reference&gt\\;ver7f30&lt\\;/reference&gt\\;&lt\\;choice value='altmeaning-,-48700,-,100'&gt\\;[preview] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='_main,0' default='yes'&gt\\;[see] (General)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-48701,-,100'&gt\\;[view] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;source&gt\\;veamos&lt\\;/source&gt\\;\" id=\"altmeaning_1\"><span class=\"systran_token_word\" value=\"0404/verb:plain*\" id=\"p1.t2_1\">we see</span></span> <span class=\"systran_token_word\" value=\"5c5d/conj\" id=\"p1.t2_2\">that</span> <span class=\"systran_altmeaning\" value=\"&lt\\;reference&gt\\;pasar7f30&lt\\;/reference&gt\\;&lt\\;choice value='altmeaning-,-35814,-,100'&gt\\;[be the matter] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35815,-,100'&gt\\;[be wrong] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35816,-,100'&gt\\;[elapse] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35817,-,100'&gt\\;[give] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35818,-,100'&gt\\;[go by] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35819,-,100'&gt\\;[go on] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35820,-,100'&gt\\;[hand down] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35821,-,100'&gt\\;[lapse] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35822,-,100'&gt\\;[move on] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35823,-,100'&gt\\;[move up] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35808,-,100'&gt\\;[outgrow] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35809,-,100'&gt\\;[outpace] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35824,-,100'&gt\\;[overtake] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35826,-,100'&gt\\;[pass down] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35827,-,100'&gt\\;[pass off] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35828,-,100'&gt\\;[pass on] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35829,-,100'&gt\\;[pass over] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='_main,0' default='yes'&gt\\;[pass] (General)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35811,-,100'&gt\\;[passage] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35830,-,100'&gt\\;[put in] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35831,-,100'&gt\\;[reach] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35832,-,100'&gt\\;[roll by] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35833,-,100'&gt\\;[roll on] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35834,-,100'&gt\\;[slip by] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;choice value='altmeaning-,-35835,-,100'&gt\\;[spend] (SYSTRAN Alternative Dictionary)&lt\\;/choice&gt\\;&lt\\;source&gt\\;pasa&lt\\;/source&gt\\;\" id=\"altmeaning_2\"><span class=\"systran_token_word\" value=\"0404/verb:plain*\" id=\"p1.t2_3\">it happens</span></span> <span class=\"systran_token_word\" value=\"5050/prep\" id=\"p1.t2_4\">with</span> <span class=\"systran_token_word\" value=\"2428/pron\" id=\"p1.t2_5\">this</span></span><br></body></html>";
        System.out.println(getInnerText(str));
    }

    public static String getInnerText(String html) {
        StringBuilder sb = new StringBuilder(html);
        Pattern pattern = Pattern.compile("(</?.[^>]+>)+?");
        Matcher matcher = pattern.matcher(sb);
        Stack<TagInfo> stack = new Stack<TagInfo>();
        String tag;
        StringBuilder textContent = new StringBuilder();
        while (matcher.find()) {
            tag = matcher.group();
            TagInfo tagInfo = new TagInfo(tag, matcher.start(), matcher.end());
            if (!stack.isEmpty() && isCloseTag(tag)) {
                TagInfo startTag = stack.peek();
                if (compareTags(startTag.getTag(), tag)) {
                    String content = sb.substring(startTag.getEnd(), tagInfo.getStart());
                    if (!content.contains("<")) {
                        textContent.append(content.trim() + " ");
                    }
                    stack.pop();
                }
                continue;
            }
            stack.push(tagInfo);
        }
        return textContent.toString();
    }


    public static boolean isCloseTag(String closeTag) {
        return closeTag.charAt(1) == '/';
    }

    public static boolean compareTags(String openTag, String closeTag) {
        Matcher m = tagPattern.matcher(openTag);
        m.find();
        return closeTag.contains(m.group());
    }

    private static class TagInfo {
        private String tag;
        private int start;
        private int end;

        public TagInfo(String tag, int start, int end) {
            this.tag = tag;
            this.start = start;
            this.end = end;
        }

        public String getTag() {
            return tag;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }


}
