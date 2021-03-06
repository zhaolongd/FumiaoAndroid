package com.fumiao.pay.bean.main;

import java.util.List;


public class AeraBean {


    /**
     * code : 110000
     * region : 北京市
     * regionEntitys : [{"code":"110100","region":"市辖区","regionEntitys":[{"code":"110101","region":"东城区"},{"code":"110102","region":"西城区"},{"code":"110105","region":"朝阳区"},{"code":"110106","region":"丰台区"},{"code":"110107","region":"石景山区"},{"code":"110108","region":"海淀区"},{"code":"110109","region":"门头沟区"},{"code":"110111","region":"房山区"},{"code":"110112","region":"通州区"},{"code":"110113","region":"顺义区"},{"code":"110114","region":"昌平区"},{"code":"110115","region":"大兴区"},{"code":"110116","region":"怀柔区"},{"code":"110117","region":"平谷区"},{"code":"110118","region":"密云区"},{"code":"110119","region":"延庆区"}]}]
     */

    private String code ="";
    private String region ="";
    private List<RegionEntitysBeanX> regionEntitys;

    @Override
    public String toString() {
        return getRegion();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<RegionEntitysBeanX> getRegionEntitys() {
        return regionEntitys;
    }

    public void setRegionEntitys(List<RegionEntitysBeanX> regionEntitys) {
        this.regionEntitys = regionEntitys;
    }

    public static class RegionEntitysBeanX {
        /**
         * code : 110100
         * region : 市辖区
         * regionEntitys : [{"code":"110101","region":"东城区"},{"code":"110102","region":"西城区"},{"code":"110105","region":"朝阳区"},{"code":"110106","region":"丰台区"},{"code":"110107","region":"石景山区"},{"code":"110108","region":"海淀区"},{"code":"110109","region":"门头沟区"},{"code":"110111","region":"房山区"},{"code":"110112","region":"通州区"},{"code":"110113","region":"顺义区"},{"code":"110114","region":"昌平区"},{"code":"110115","region":"大兴区"},{"code":"110116","region":"怀柔区"},{"code":"110117","region":"平谷区"},{"code":"110118","region":"密云区"},{"code":"110119","region":"延庆区"}]
         */
        @Override
        public String toString() {
            return getRegion();
        }

        private String code ="";
        private String region = "";
        private List<RegionEntitysBean> regionEntitys;


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public List<RegionEntitysBean> getRegionEntitys() {
            return regionEntitys;
        }

        public void setRegionEntitys(List<RegionEntitysBean> regionEntitys) {
            this.regionEntitys = regionEntitys;
        }

        public static class RegionEntitysBean {
            /**
             * code : 110101
             * region : 东城区
             */

            private String code ="";
            private String region = "";

            @Override
            public String toString() {
                return getRegion();
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

        }
    }
}
