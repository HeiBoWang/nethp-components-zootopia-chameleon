package ${groupId}.web.configuration.database;

import com.jd.medicine.base.common.console.ConsoleSQL;
import com.jd.medicine.base.common.sequence.RxSequence;
import com.jd.medicine.base.common.sequence.RxSequenceUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 自增序列
 *
 * @author mengfei6
 * @date 2020/2/28 13:39
 */
@Configuration
public class SequencesConfig {

    @Bean
    public RxSequenceUtil rxSequenceUtil(DataSource dataSource) {
        RxSequenceUtil rxSequenceUtil = new RxSequenceUtil();
        rxSequenceUtil.setDefaultSequence(defaultSequence(dataSource));
        Map<String, RxSequence> sequenceMap = new HashMap<>(10);
        rxSequenceUtil.setSequenceMap(sequenceMap);
        sequenceMap.put("RX_DISPACH_01", sequence1(dataSource));
        sequenceMap.put("RX_DISPACH_02", sequence2(dataSource));
        sequenceMap.put("RX_DISPACH_03", sequence3(dataSource));
        sequenceMap.put("RX_DISPACH_04", sequence4(dataSource));
        sequenceMap.put("RX_DISPACH_05", sequence5(dataSource));
        sequenceMap.put("RX_DISPACH_06", sequence6(dataSource));
        return rxSequenceUtil;
    }

    @Bean
    public RxSequence defaultSequence(DataSource dataSource) {
        RxSequence rxSequence = new RxSequence();
        rxSequence.setDataSource(dataSource);
        rxSequence.setStartValue(10000000);
        rxSequence.setBlockSize(100);
        rxSequence.setStepSize(6);
        return rxSequence;
    }

    @Bean
    public RxSequence sequence1(DataSource dataSource) {
        RxSequence rxSequence = new RxSequence();
        rxSequence.setDataSource(dataSource);
        rxSequence.setStartValue(10000001);
        rxSequence.setBlockSize(100);
        rxSequence.setStepSize(6);
        return rxSequence;
    }

    @Bean
    public RxSequence sequence2(DataSource dataSource) {
        RxSequence rxSequence = new RxSequence();
        rxSequence.setDataSource(dataSource);
        rxSequence.setStartValue(10000002);
        rxSequence.setBlockSize(100);
        rxSequence.setStepSize(6);
        return rxSequence;
    }

    @Bean
    public RxSequence sequence3(DataSource dataSource) {
        RxSequence rxSequence = new RxSequence();
        rxSequence.setDataSource(dataSource);
        rxSequence.setStartValue(10000003);
        rxSequence.setBlockSize(100);
        rxSequence.setStepSize(6);
        return rxSequence;
    }

    @Bean
    public RxSequence sequence4(DataSource dataSource) {
        RxSequence rxSequence = new RxSequence();
        rxSequence.setDataSource(dataSource);
        rxSequence.setStartValue(10000004);
        rxSequence.setBlockSize(100);
        rxSequence.setStepSize(6);
        return rxSequence;
    }

    @Bean
    public RxSequence sequence5(DataSource dataSource) {
        RxSequence rxSequence = new RxSequence();
        rxSequence.setDataSource(dataSource);
        rxSequence.setStartValue(10000005);
        rxSequence.setBlockSize(100);
        rxSequence.setStepSize(6);
        return rxSequence;
    }

    @Bean
    public RxSequence sequence6(DataSource dataSource) {
        RxSequence rxSequence = new RxSequence();
        rxSequence.setDataSource(dataSource);
        rxSequence.setStartValue(10000006);
        rxSequence.setBlockSize(100);
        rxSequence.setStepSize(6);
        return rxSequence;
    }



    @Bean
    public ConsoleSQL consoleSQL(DataSource dataSource){
        ConsoleSQL consoleSQL = new ConsoleSQL();
        consoleSQL.setDataSource(dataSource);
        return consoleSQL;
    }

}
