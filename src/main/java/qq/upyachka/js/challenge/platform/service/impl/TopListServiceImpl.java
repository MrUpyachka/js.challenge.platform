package qq.upyachka.js.challenge.platform.service.impl;

import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import qq.upyachka.js.challenge.core.model.ScriptExecutionResultDo;
import qq.upyachka.js.challenge.core.repository.ScriptResultRepository;
import qq.upyachka.js.challenge.platform.script.ScriptExecutionResultDto;
import qq.upyachka.js.challenge.platform.script.ScriptExecutionResultParser;
import qq.upyachka.js.challenge.platform.service.TopListService;

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
    public List<ScriptExecutionResultDto> getTop100() {
        final Pageable top100 = new PageRequest(0, 100);
        final Page<ScriptExecutionResultDo> page = executions.getTop(top100);
        LOG.debug("Found {} executions in TOP100", page.getTotalElements());
        return ScriptExecutionResultParser.parse(Lists.newArrayList(page.iterator()));
    }
}
