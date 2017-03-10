package qq.upyachka.js.contest.platform.service.impl;

import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import qq.upyachka.js.contest.core.dto.ScriptExecutionResultDto;
import qq.upyachka.js.contest.core.dto.utils.ScriptExecutionResultCopyUtils;
import qq.upyachka.js.contest.core.model.script.ScriptExecutionResultDo;
import qq.upyachka.js.contest.core.repository.ScriptResultRepository;
import qq.upyachka.js.contest.platform.service.TopListService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of {@link TopListService}.
 * Created on 03.03.17.
 * @author upyachka.
 */
@Service
public class TopListServiceImpl implements TopListService {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(TopListServiceImpl.class);

    /** Access to executions data. */
    @Autowired
    private ScriptResultRepository executions;

    @Override
    @Transactional
    public List<ScriptExecutionResultDto> getTop100(Long taskId) {
        final Pageable top100 = new PageRequest(0, 100);
        final Page<ScriptExecutionResultDo> page = executions.getTop(taskId, top100);
        LOG.debug("Found {} executions in TOP100", page.getTotalElements());
        return ScriptExecutionResultCopyUtils.parse(Lists.newArrayList(page.iterator()));
    }
}
