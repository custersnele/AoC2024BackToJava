package ccfun.day5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PrintRuleComparator implements Comparator<Integer> {
	private List<PrintRule> rules = new ArrayList<>();

	public PrintRuleComparator(List<PrintRule> rules) {
		this.rules = rules;
	}

	@Override
	public int compare(Integer o1, Integer o2) {
		PrintRule rule = findRule(o1, o2);
		if (rule.isBefore(o1, o2)) {
			return -1;
		} else {
			return 1;
		}
	}

	public PrintRule findRule(int o1, int o2) {
		for (PrintRule rule : rules) {
			if (rule.contains(o1, o2)) {
				return rule;
			}
		}
		return null;
	}


}
