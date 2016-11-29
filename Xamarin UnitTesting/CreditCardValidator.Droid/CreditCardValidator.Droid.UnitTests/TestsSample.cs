using System;
using NUnit.Framework;

namespace CreditCardValidator.Droid.UnitTests
{
	[TestFixture]
	public class TestsSample
	{
		
		[SetUp]
		public void Setup ()
		{
		}

		
		[TearDown]
		public void Tear ()
		{
		}

		[Test]
		public void Pass ()
		{
			SomeNamespace.CreditCardUtility util = new SomeNamespace.CreditCardUtility ();

			Assert.AreEqual ("Sunil", util.getName());
		}

		[Test]
		public void Fail ()
		{
			Assert.False (true);
		}

		[Test]
		[Ignore ("another time")]
		public void Ignore ()
		{
			Assert.True (false);
		}

		[Test]
		public void Inconclusive ()
		{
			Assert.Inconclusive ("Inconclusive");
		}
	}
}

