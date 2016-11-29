using System;
using System.IO;
using System.Linq;
using NUnit.Framework;
using Xamarin.UITest;
using Xamarin.UITest.Android;
using Xamarin.UITest.Queries;
using CreditCardValidator.Droid;
using CreditCardValidation.Common;

namespace CreditCardValidator.Droid.UITests
{
	[TestFixture]
	public class Tests
	{
		AndroidApp app;

		[SetUp]
		public void BeforeEachTest()
		{
//			app = ConfigureApp.Android.StartApp();

//			ConfigureApp.Android.ApkFile("../CreditCardValidator.Droid/bin/Release/com.xamarin.example.creditcardvalidator.apk");
//
//			app = ConfigureApp.Android.StartApp();

//			string currentFile = new Uri(Assembly.GetExecutingAssembly().CodeBase).LocalPath;
//			FileInfo fi = new FileInfo(currentFile);
//			string dir = fi.Directory.Parent.Parent.Parent.FullName;
//
//			// PathToAPK is a property or an instance variable in the test class
//			string PathToAPK = Path.Combine(dir, "CreditCardValidator.Droid", "bin", "Release", "com.xamarin.example.creditcardvalidator.apk");
//			app = ConfigureApp.Android.ApkFile (PathToAPK).StartApp ();
			string apkpath = "../../../CreditCardValidator.Droid/bin/Release/com.xamarin.example.creditcardvalidator.apk";
			app = ConfigureApp.Android.Debug ().ApkFile (apkpath).StartApp ();
		}

		[Test]
		public void CreditCardNumber_Valid()
		{
			app.WaitForElement(c => c.Marked("action_bar_title").Text("Enter Credit Card Number"));
			app.EnterText(c=>c.Marked("creditCardNumberText"), new string('1', 16));
			app.Tap(c => c.Marked("validateButton"));
			app.WaitForElement(c => c.Marked("validationSuccessMessage").Text("The credit card number is valid!"));

			app.ClearText ();
		}

		[Test]
		public void CreditCardNumber_TooShort_DisplayErrorMessage()
		{
			app.WaitForElement(c => c.Marked("action_bar_title").Text("Enter Credit Card Number"));
			app.EnterText(c=>c.Marked("creditCardNumberText"), new string('2', 15));
			app.Tap(c => c.Marked("validateButton"));

			app.WaitForElement(c => c.Marked("errorMessagesText").Text("Credit card number is too short."));
			app.ClearText ();
		}

		[Test]
		public void CreditCardNumber_TooLong_DisplayErrorMessage()
		{
			app.WaitForElement(c => c.Marked("action_bar_title").Text("Enter Credit Card Number"));
			app.EnterText(c=>c.Marked("creditCardNumberText"), new string('3', 17));
			app.Tap(c => c.Marked("validateButton"));

			app.WaitForElement(c => c.Marked("errorMessagesText").Text("Credit card number is too long."));
			app.ClearText ();



		}

		[Test]
		public void UnitTest_CreditCard(){
			SimpleCreditCardValidator validate = new SimpleCreditCardValidator ();
			string errorMessage;
			validate.IsCCValid ("99999", out errorMessage);
			Assert.IsNotNull(errorMessage);
		}

	}
}

