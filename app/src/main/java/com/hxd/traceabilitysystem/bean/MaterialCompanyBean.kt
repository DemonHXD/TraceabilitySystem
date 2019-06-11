package com.hxd.traceabilitysystem.bean

/**
 *  项目名：    TraceabilitySystem
 *  包名：      com.hxd.traceabilitysystem.bean
 *  文件名:     MaterialCompanyBean
 *  创建者:     HXD
 *  创建时间:   2019/6/11 10:06
 *  描述:       原材料公司实体类
 */

/**
 * name:永州市原材料提供公司
 * registeredCapital:注册资本
 * taxpayerRegistration:纳税人识别号
 * dateOfEstablishment:工商注册号
 * businessRegistrationNumber:组织机构代码
 * registrationAuthority:登记机关
 * registeredAddress:注册地址
 * scope:经营范围
 * dateOfApproval:核准日期
 */
data class MaterialCompanyBean(
    val name: String = "",
    val registeredCapital: String = "",
    val taxpayerRegistration: String = "",
    val dateOfEstablishment: String = "",
    val businessRegistrationNumber: String = "",
    val registrationAuthority: String = "",
    val registeredAddress: String = "",
    val scope: String = "",
    val dateOfApproval: String = ""
)