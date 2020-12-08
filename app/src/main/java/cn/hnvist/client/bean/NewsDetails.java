package cn.hnvist.client.bean;

import java.util.List;

public class NewsDetails {
    /**
     * jsonp : {"data":{"type":"1","id":"1574154873009","image":"http://mob.hnvist.cn/mp//file/1573800566036065759.png/?filePath=/homeService/2019/12/18/13b3b2a4-913d-4a36-a0ae-7beb72629f14.png","name":"我校连续两年学生毕业设计抽查合格率为100%","desc":"近日，湖南省教育厅正式发布了《关于201","author":null,"content":"<p >    近日，湖南省教育厅正式发布了《关于2019年高职高专院校学生毕业设计抽查情况的通报》。我院应用英语专业、人力资源管理专业等两个专业，在2019年高职高专院校学生毕业设计抽查中合格率达到100%。今年湖南省教育厅抽取的69所高职高专院校中，合格率达到100%的学校只有31所,我院学生毕业设计平均分排名在第13位。去年我院毕业设计抽查到的工程安全评价与监理和会计专业合格率也到达了100%。<br >    毕业设计是高职院校人才培养的重要环节，也是检验学校办学质量的重要指标。为保障毕业设计的科学性和规范性，近年来我院根据省厅历年的毕业设计文件精神，不断完善学生毕业设计管理办法，建立了学校、二级学院和专业团队三级的联动检查把关机制，统筹推进毕业设计工作的规范化，确保了毕业设计成果质量。<\/p>\n<p >成绩的取得离不开全院师生的共同努力，正是指导老师们科学严谨的治学态度和同学们不畏艰难的求学精神，让学院的毕业设计抽查成绩年年攀升。我们将以此为契机，在努力建设高等职业教育\u201c双一流\u201d特色专业群的进程中，不断优化专业结构，加强专业建设和教学管理，努力提高人才培养的质量。<\/p>\n<p ><\/p>","publishTime":"2019-11-15","loadTime":"1574327100476","imageList":["http://mob.hnvist.cn/mp//file/1573800566036065759.png/?filePath=/homeService/2019/11/21/f78a5d46-07df-4e19-a05a-d9ba1d0617c6.png"]},"status":0,"code":200,"msg":"成功获取数据"}
     */

    private JsonpBean jsonp;

    public JsonpBean getJsonp() {
        return jsonp;
    }

    public void setJsonp(JsonpBean jsonp) {
        this.jsonp = jsonp;
    }

    public static class JsonpBean {
        /**
         * data : {"type":"1","id":"1574154873009","image":"http://mob.hnvist.cn/mp//file/1573800566036065759.png/?filePath=/homeService/2019/12/18/13b3b2a4-913d-4a36-a0ae-7beb72629f14.png","name":"我校连续两年学生毕业设计抽查合格率为100%","desc":"近日，湖南省教育厅正式发布了《关于201","author":null,"content":"<p >    近日，湖南省教育厅正式发布了《关于2019年高职高专院校学生毕业设计抽查情况的通报》。我院应用英语专业、人力资源管理专业等两个专业，在2019年高职高专院校学生毕业设计抽查中合格率达到100%。今年湖南省教育厅抽取的69所高职高专院校中，合格率达到100%的学校只有31所,我院学生毕业设计平均分排名在第13位。去年我院毕业设计抽查到的工程安全评价与监理和会计专业合格率也到达了100%。<br >    毕业设计是高职院校人才培养的重要环节，也是检验学校办学质量的重要指标。为保障毕业设计的科学性和规范性，近年来我院根据省厅历年的毕业设计文件精神，不断完善学生毕业设计管理办法，建立了学校、二级学院和专业团队三级的联动检查把关机制，统筹推进毕业设计工作的规范化，确保了毕业设计成果质量。<\/p>\n<p >成绩的取得离不开全院师生的共同努力，正是指导老师们科学严谨的治学态度和同学们不畏艰难的求学精神，让学院的毕业设计抽查成绩年年攀升。我们将以此为契机，在努力建设高等职业教育\u201c双一流\u201d特色专业群的进程中，不断优化专业结构，加强专业建设和教学管理，努力提高人才培养的质量。<\/p>\n<p ><\/p>","publishTime":"2019-11-15","loadTime":"1574327100476","imageList":["http://mob.hnvist.cn/mp//file/1573800566036065759.png/?filePath=/homeService/2019/11/21/f78a5d46-07df-4e19-a05a-d9ba1d0617c6.png"]}
         * status : 0
         * code : 200
         * msg : 成功获取数据
         */

        private DataBean data;
        private int status;
        private int code;
        private String msg;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public static class DataBean {
            /**
             * type : 1
             * id : 1574154873009
             * image : http://mob.hnvist.cn/mp//file/1573800566036065759.png/?filePath=/homeService/2019/12/18/13b3b2a4-913d-4a36-a0ae-7beb72629f14.png
             * name : 我校连续两年学生毕业设计抽查合格率为100%
             * desc : 近日，湖南省教育厅正式发布了《关于201
             * author : null
             * content : <p >    近日，湖南省教育厅正式发布了《关于2019年高职高专院校学生毕业设计抽查情况的通报》。我院应用英语专业、人力资源管理专业等两个专业，在2019年高职高专院校学生毕业设计抽查中合格率达到100%。今年湖南省教育厅抽取的69所高职高专院校中，合格率达到100%的学校只有31所,我院学生毕业设计平均分排名在第13位。去年我院毕业设计抽查到的工程安全评价与监理和会计专业合格率也到达了100%。<br >    毕业设计是高职院校人才培养的重要环节，也是检验学校办学质量的重要指标。为保障毕业设计的科学性和规范性，近年来我院根据省厅历年的毕业设计文件精神，不断完善学生毕业设计管理办法，建立了学校、二级学院和专业团队三级的联动检查把关机制，统筹推进毕业设计工作的规范化，确保了毕业设计成果质量。</p>
             <p >成绩的取得离不开全院师生的共同努力，正是指导老师们科学严谨的治学态度和同学们不畏艰难的求学精神，让学院的毕业设计抽查成绩年年攀升。我们将以此为契机，在努力建设高等职业教育“双一流”特色专业群的进程中，不断优化专业结构，加强专业建设和教学管理，努力提高人才培养的质量。</p>
             <p ></p>
             * publishTime : 2019-11-15
             * loadTime : 1574327100476
             * imageList : ["http://mob.hnvist.cn/mp//file/1573800566036065759.png/?filePath=/homeService/2019/11/21/f78a5d46-07df-4e19-a05a-d9ba1d0617c6.png"]
             */

            private String type;
            private String id;
            private String image;
            private String name;
            private String desc;
            private Object author;
            private String content;
            private String publishTime;
            private String loadTime;
            private List<String> imageList;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public Object getAuthor() {
                return author;
            }

            public void setAuthor(Object author) {
                this.author = author;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getLoadTime() {
                return loadTime;
            }

            public void setLoadTime(String loadTime) {
                this.loadTime = loadTime;
            }

            public List<String> getImageList() {
                return imageList;
            }

            public void setImageList(List<String> imageList) {
                this.imageList = imageList;
            }
        }
    }
}
