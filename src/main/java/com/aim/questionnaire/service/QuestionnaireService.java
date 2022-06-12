package com.aim.questionnaire.service;

import com.aim.questionnaire.common.utils.DateUtil;
import com.aim.questionnaire.common.utils.UUIDUtil;
import com.aim.questionnaire.dao.QuestionnaireEntityMapper;
import com.aim.questionnaire.dao.entity.QuestionnaireEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Patrick Zhu
 * @since 2022.6.11
 */
@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    /**
     * 添加问卷
     *
     * @param questionnaireEntity
     * @return
     */
    public int addQuestionnaire(QuestionnaireEntity questionnaireEntity, String user) {
        String id = UUIDUtil.getOneUUID();
        questionnaireEntity.setId(id);
        questionnaireEntity.setProjectId("test");

        int result = questionnaireEntityMapper.insertSelective(questionnaireEntity);
        return result;
    }

    /**
     * 修改问卷
     *
     * @param questionnaireEntity
     * @return
     */
    public int modifyQuestionnaireInfo(QuestionnaireEntity questionnaireEntity, String user) {
        // 获取当前时间
        Date date = DateUtil.getCreateTime();
        questionnaireEntity.setLastUpdateDate(date);
        //获取用户信息
        questionnaireEntity.setLastUpdatedBy(user);
        int result = questionnaireEntityMapper.updateByPrimaryKeySelective(questionnaireEntity);
        return result;
    }

    /**
     * 删除问卷
     *
     * @param questionnaireEntity
     * @return
     */
    public int deleteQuestionnaireById(QuestionnaireEntity questionnaireEntity) {
        String questionnaireId = questionnaireEntity.getId();
        int result = questionnaireEntityMapper.deleteByPrimaryKey(questionnaireId);

        return result;
    }

    /**
     * 查询问卷列表
     *
     * @param questionnaireEntity
     * @return
     */
    public List<Object> queryQuestionnaireList(QuestionnaireEntity questionnaireEntity) {
        List<Object> resultList = new ArrayList<Object>();
        if ("".equals(questionnaireEntity.getQuestionName())) {
            questionnaireEntity.setQuestionName(null);
        }

        String id = questionnaireEntity.getId();
        List<Map<String, Object>> proResult = questionnaireEntityMapper.queryQuestionListByProjectId(id);
        for (Map<String, Object> proObj : proResult) {
            resultList.add(proObj);
        }
        return resultList;
    }

}