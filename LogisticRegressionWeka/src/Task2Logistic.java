
import java.io.File;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.functions.Logistic;
import weka.classifiers.Evaluation;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
public class Task2Logistic {

	public static void main(String[] args)throws Exception {
		System.out.println("results are being calculated");
	DataSource source = new DataSource("C:/Users/Anurag/Desktop/task2.arff");
	Instances dataset = source.getDataSet();
	dataset.setClassIndex(dataset.numAttributes()-17);
	AttributeSelection filter = new AttributeSelection();
	CfsSubsetEval eval1 = new CfsSubsetEval();
	GreedyStepwise search = new GreedyStepwise();
	search.setSearchBackwards(true);
	filter.setEvaluator(eval1);
	filter.setSearch(search);
	filter.setInputFormat(dataset);
	Instances newdata = Filter.useFilter(dataset, filter);

	System.out.println(newdata.toString());
	Logistic log = new Logistic();

	log.buildClassifier(newdata);
	
		Evaluation eval = new Evaluation(newdata);
		
		eval.evaluateModel(log, newdata);
		System.out.println(eval.toSummaryString("Evaluation results: \n", false));
		System.out.println(" \n correct % = "+eval.pctCorrect());
		System.out.println("Incorrect % = "+eval.pctIncorrect());
		System.out.println("precision = "+eval.precision(1));
		System.out.println("REcall = "+eval.recall(1));
		

	}

}
